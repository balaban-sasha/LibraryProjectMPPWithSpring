/**
 * Created by Саша on 09.05.2017.
 */
$(document).ready(function () {
    var params = window
        .location
        .search
        .replace('?','')
        .split('&')
        .reduce(
            function(p,e){
                var a = e.split('=');
                p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                return p;
            },
            {}
        );
    function get_books_list() {
        $('.list-group').html('');
        $.ajaxSetup({
            url: '/library',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=Book&limit=10"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].bookName != "")
                            $('.list-group').append('<a href="http://localhost:8080/books?bookId=' + obj[i].id + '"class="list-group-item">' + obj[i].bookName + ' </a>');
                    }
                }
            }
        })

    }
    function get_books_page_content() {
        $('.content-container').html('');
        var page=params['page'];
        if(page==null)
            page=1;
        $.ajaxSetup({
            url: '/books',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=Book&limit=12&page="+page
        });
        $.ajax({
            success: function (msg_j) {
                console.log(msg_j);
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].bookName != "") {
                            var genreName="";
                            if(obj[i].bookGenre!=null)
                            {
                                genreName+="<font style='font-size:18px'>Жанры:</font>";
                                for (var j = 0; j < obj[i].bookGenre.length; j++)
                                {
                                   genreName+=obj[i].bookGenre[j].genreName+", ";
                                }
                            }
                            $('.content-container').append('<div class="col-md-4 book-content-table"> <h2>' + obj[i].bookName + '</h2><p>' + (obj[i].bookDescription).substring(0, 255) + '</p>'
                            +'<p>'+genreName+'</p>' +
                            '<p><a class="btn btn-default" href="books?bookId=' + obj[i].id + '" role="button">Подробнее»</a></p></div>'
                        )
                        }
                    }
                }
            }
        })
        $.ajaxSetup({
            url: '/books',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Book&action=GetAll"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;

                    $(function() {
                        $('.pagination').pagination({
                            items: msg_j.length,
                            itemsOnPage: 12,
                            cssStyle: 'light-theme'
                        });
                    });
                    if(params['page']!=null)
                    {
                        $(function() {
                            $('.pagination').pagination('selectPage', params['page']);
                        });
                    }
                }
            }
        })

    }
    function get_books_comment_content() {
        $('.books-comment-container').html('');
        var id=$('#books-id').val();
        $.ajaxSetup({
            url: '/books',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=Comment&limit=20&Id="+id
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].text != "")
                                $('.books-comment-container').append('<blockquote style="    background: darkkhaki;"><font style="font-size: 20px;'+
                                    'font-weight: 700;">Автор:</font>'+obj[i].userName+'<br><font style=" font-size: 20px;font-weight: 700;">Дата:</font>'+
                                    obj[i].publicateDate+'<br>'+obj[i].text+'</blockquote>');
                    }
                }
            }
        })

    }
    $('#add_new_comment').click(function()
    {
        var message=$('#message').val();
        var id=$('#books-id').val();
        $.ajax({
            type:'POST',
            url:'/books',
            data:{commentBookId:id,commentText:message,commentTextEn:"",action:"insert",comentSenderId:0,table:"Comment"},
            success:function(response){	window.location.reload();
            },
            error:function (response) {
                window.location.reload();

            }
        });
    });
    $(window).load(function()
    {

        if ($('.books-comment-container').val()!=null)
            get_books_comment_content();
        get_books_page_content();
        get_books_list();
    });

})