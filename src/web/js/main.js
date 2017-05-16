/**
 * Created by Саша on 24.04.2017.
 */
$(document).ready(function () {
    function getUserLogin() {
        $('.user-name').html('');
        $.ajaxSetup({
            url: '/library',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getUserById&tableName=User"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    $('.user-name').append('Добро пожаловать: '+obj[0].login);
                }
            }
        })
    }
    function get_news_list() {
        $('.list-group').html('');
        $.ajaxSetup({
            url: '/library',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=News&limit=10"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].header != "")
                            $('.list-group').append('<a href="http://localhost:8080/news?newsId=' + obj[i].id + '"class="list-group-item">' + obj[i].header + ' </a>');
                    }
                }
            }
        })

    }
    function get_main_page_content() {
        $('.list-group').html('');
        $.ajaxSetup({
            url: '/library',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getOneSection&rowName=Главная&tableName=Section"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    console.log(msg_j);
                    var obj = msg_j;
                    if (obj != null) {
                        if (obj[0] != null) {
                            $('#main_section_header').html(obj[0].header);
                            $('#main_section_description').html(obj[0].description);
                        }
                    }
                }
            }
        })

    }

    $('#user_registry_button').click(function () {
        var r_user_password = $('#r_user_password').val();
        var r_user_password_try = $('#r_user_password_try').val();
        var r_user_login = $('#r_user_login').val();
        var r_user_name = $('#r_user_name').val();
        var r_user_surname = $('#r_user_surname').val();
        var r_user_age = $('#r_user_age').val();
        var r_user_gender = $('#r_user_gender').val();
        var div = document.getElementById("errors");
        var flag = true;
        if (flag) {
            if ((r_user_password != '') && (r_user_password_try != '') && (r_user_login != '')) {
                if (r_user_password != r_user_password_try) {
                    div.innerHTML = "<font color='red'>Пароли не совпадают!</font>";
                }
                else {
                    $.ajax({
                        type: 'POST',
                        url: '/library?tableName=User&action=insert',
                        dataType: "json",
                        data: {
                            table: "User",
                            userLogin: r_user_login,
                            userName: r_user_name,
                            userGender: r_user_gender,
                            userAge: r_user_age,
                            userSurname: r_user_surname,
                            userPassword: r_user_password
                        },
                        success: function (response) {
                            if (response === 'success') {
                                $('#r_user_login').val('');
                                $('#r_user_password_try').val('');
                                $('#r_user_password').val('');
                            }
                            else
                                div.innerHTML = "<font color='red'>error:" + response + "</font>";
                        },
                        error: function (response) {
                            window.location.reload();
                            $('#r_user_login').val('');
                            $('#r_user_password_try').val('');
                            $('#r_user_password').val('');
                        },
                        async: false
                    });
                }
            }
            else
                div.innerHTML = "<font color='red'>Необходимо заполнить все обязательные поля!</font>";
        }
    });
    $('#user_log_in').click(function () {
        var user_login = $('#user_login').val();
        var user_password = $('#user_password').val();
        $.ajax({
            type: 'POST',
            url: '/library',
            data: {table: "User", userLogin: user_login, userPassword: user_password, action: "auth"},
            success: function (response) {
                window.location.reload();

            },
            error: function (response) {
                window.location.reload();
            },
            async: false
        });

    });
    $('#exit_button').click(function () {
        $.ajax({
            type: 'POST',
            url: '/library',
            data: {action: 'closeSession'},
            success: function (response) {
                window.location.reload();

            },
            error: function (response) {
                window.location.reload();

            }
        })

    })
    if ($('#message_box').length) {
        function send_message() {
            var chatMessage = $('#chat_message').val();
            $('#chat_message').val('');
            if (chatMessage != '')
                $.ajax({
                    type: 'POST',
                    url: '/chat',
                    data: {action: 'insert', table: 'Chat', chatText: chatMessage, chatTextEn: ""},
                    success: function (response) {
                        $('#chat_message').val('');
                    }
                });
        }

        $('#chat_message').keyup(function (e) {
            if (e.keyCode == 13) {
                send_message();
            }
        });
        $('#send_chat_message').click(function () {
            send_message();
        });
        var flag = false;
        var last_message_id = 0;

        function get_chat_message() {

            $.ajaxSetup({
                url: '/chat',
                global: true,
                type: 'GET',
                data: "createAction=GetChatMessages&tableName=Chat&event=get&id=" + last_message_id + "&t=" + (new Date).getTime()
            });
            $.ajax({
                success: function (msg_j) {
                    if (msg_j.length > 0) {
                        var obj = msg_j;
                        var i = obj.length - 1;
                        while (i >= 0) {
                            if (obj[i].avatar == '')
                                obj[i].avatar = 'img/nobody-avatar.png';
                            $('#message_box').append(' <li class="media"><div class="media-body"> <div class="media"><a class="pull-left" href="#">' +
                                '<img class="media-object img-circle " style="max-width:155px;" src="' + obj[i].userAvatar + '"></a><div class="media-body"><p style="word-wrap:break-word; max-width:' + $('.media-list').width() + 'px" >' + obj[i].text + '</p><br>' +
                                '<small class="text-muted">' + obj[i].userName + ' | ' + (obj[i].publicateDate) + '</small></div></div><hr></div></li>');
                            last_message_id = obj[i].id;
                            i--;
                        }
                    }
                    $('#message_box').scrollTop(1000000);
                },
                error: function (msg_j) {
                    console.log("error");

                },
                async: false
            })
        }

        var i = 1;
        setTimeout(function get_new_message() {
            get_chat_message();
            setTimeout(get_new_message, 2000);
        }, 100);


    }


    function get_users_online() {
        $('#online_users_box').html('');
        $.ajaxSetup({
            url: '/chat',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "createAction=getOnlineUsers&tableName=User"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].userAvatar == '')
                            obj[i].userAvatar = 'img/nobody-avatar.png';
                        $('#online_users_box').append(' <li class="media" id="chat_user_info"><div class="media-body"><div class="media"><a class="pull-left" href="#">' +
                            ' <img class="media-object img-circle" style="max-height:40px;" src="' + obj[i].userAvatar + '"> </a><div class="media-body">' +
                            '<h5><a href="http://localhost:8080/user/' + obj[i].login + '">' + obj[i].login + ' </a>|' + obj[i].status + ' </h5>' +
                            '<small class="text-muted"style="color:green" >Online</small></div></div></div></li>');
                    }
                }
            },
            async: false
        })

    }

    $(window).load(function () {
        get_users_online();
        get_main_page_content();
        get_news_list();
        getUserLogin();
    });
    function send_user_online() {
        $.ajax({
            type: 'POST',
            url: '/library',
            data: {action: "updateUserOnline"},
            success: function (response) {
            }
        });
    }

    setTimeout(function set_user_online() {
        send_user_online();
        get_users_online();
        get_news_list();
        setTimeout(set_user_online, 40000);
    }, 100);


});