const ApiServerUrl = "http://localhost:8080/api/v1/"

class Requester {

    getBooks(query, page = 1, callback) {
        this.request(ApiServerUrl + 'book/search', 'GET', {'query': query, 'page': page}, callback);
    }

    getKeywordTop10(callback) {
        this.request(ApiServerUrl + 'book/history/top10', 'GET', {}, callback);
    }

//    insertPosts(title = "", writer = "", content = "", callback) {
//        this.request(`${ApiServerUrl}posts/`, 'POST', JSON.stringify({'title': title, 'writer': writer, 'content': content}), callback);
//    }

    request(url, type, data, successCallback, errorCallback = console.log('errorCallback not defined.')) {
        $.ajax({
            url: url,
            type: type,
            contentType:"application/json; charset=UTF-8",
            dataType: 'json',
            data: data,
            success: function(result) {
                console.log(result)
                successCallback(result)
            },
            error: errorCallback
        });
    }
}
