const paginationInfo = {
    bookInfoPerPage: 10,
    pageBlockNum: 5,
    currentPage: 1,
    keyword: ''
}

const requester = new Requester();

$(document).ready(function() {
    requester.getKeywordTop10(insertKeywordTop10);

    $('#search-button').click(() => {
        if ($('#search-keyword').val() === '') {
            return;
        }
        paginationInfo.keyword = $('#search-keyword').val();
        $('#search-keyword').val('');
        loadData(1);
    });

    $('#login-button').click(() => {
        window.location.href = 'http://localhost:8080/login'
    });

    $('#registration-button').click(() => {
        window.location.href = 'http://localhost:8080/registration'
    });
});

function insertKeywordTop10(ranking) {
    $('#keyword-top10').html("");
    ranking.map(rank => {
        `<span>${rank.keyword}(${rank.count})</span>&nbsp&nbsp`
    })
}

function loadData(page) {
    requester.getBooks(paginationInfo.keyword, page, insertBookList);
    paginationInfo.currentPage = page;
}ㅊ

function insertBookList(searchInfo) {
    bookList = searchInfo.searchResults;
    bookList.map(d => {
        new BookInfo(d.title, d.thumbnail, d.content, d.isbn, d.publisher, d.publishingDate, d.price, d.salePrice)
    });

    $("#list-content").html("");
    let html = "";
    bookList.forEach(bookInfo => {
        html += `
        <div class="book-content">
            <img src=${bookInfo.thumbnail} style="width: 108px; height: 108px"></img>
            <div style="display: inline-block">
                <div>제목 : ${bookInfo.title}</div>
                <div>작가 : ${bookInfo.author}</div>
                <div>출판사 : ${bookInfo.publisher}</div>
                <div>출판일 : ${bookInfo.publishingDate}</div>
                <div>판매가 : ${bookInfo.salePrice}</div>
            </div>
        </div>`
    });
    $("#list-content").html(html);

    insertPagination(searchInfo.searchMetaInfo.totalDoc);
}

function insertPagination(totalBooksNum) {
    $("#pagination-area").html("");
    let html = "";
    let totalPageNum = Math.ceil(totalBooksNum / paginationInfo.bookInfoPerPage);
    const startPageNum = Math.ceil(paginationInfo.currentPage / paginationInfo.pageBlockNum);
    let endPageNum = startPageNum + paginationInfo.pageBlockNum - 1;
    totalPageNum = totalPageNum > 0? totalPageNum: 1
    endPageNum = (endPageNum > totalPageNum)? totalPageNum: endPageNum;

    if (startPageNum > paginationInfo.pageBlockNum) {
        html += `
            <div id="pre-area" class="inline-block">
                <span>
                    <a onClick='loadData(startPageNum - 1)'><</a>
                </span>
            </div>`
    }

    html += `<div id="paging-area" class="inline-block">`

    for( let i = startPageNum; i <= endPageNum; ++i ) {
        if (i == paginationInfo.currentPage) {
            html += `<span id='current-page' class='page-num' onClick='loadData(${i})'>${i}</span>`
        } else {
            html +=
                `<span class='page-num'>
                <a onClick='loadData(${i})'>${i}</a>
            </span>`
        }
    }

    html += `</div>`

    if (endPageNum < totalPageNum) {
        html += `
            <div id="next-area" class="inline-block">
                <span>
                    <a onClick='loadData(endPageNum + 1)'>></a>
                </span>
            </div>`
    }
    $("#pagination-area").html(html);
}