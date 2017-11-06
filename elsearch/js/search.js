/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





function getsearch()
{
    var query = $.trim($("#strquery").val());
    var type = $("#type").val();
    var data;
    var jsonquery = '{"rows": [{"keyword": "'+query+'", "querytype": "and", "queryfield":"'+ type + '"}], "total": 1, "usertype": [], "timestart": "", "timesend": ""}'
//    $.get("./Queryjson.php", jsonquery, function (e){   
//        var e = e.replace(/^\s+|\s+$/g, "");
//        e = $.parseJSON(e);      
//        e=e.rows;
//        data=e;
//        $('#table').bootstrapTable("load",data);
//    });
    $.ajax({  
            async: true,  
            type: "post",  
            url: "Queryjson.php",  
            data: jsonquery,  
            dataType: "json",  
        timeout: 5000,
        contentType: "application/json; charset=utf-8",
            success: function (data) {  
            $('#form1 #search_submit').attr('disabled', false);
            data = data.rows;
            $('#table').bootstrapTable("load", data);
            },
                          beforeSend: function ()
            {   //触发ajax请求开始时执行
                //$('#advanced-search-form #search_submit').text('搜索中...');
                $('#form1 #search_submit').attr('disabled', true); //改变提交按钮上的文字并将按钮设置为不可点击
            },
            error: function (returndata)
            {
                //$('#advanced-search-form #search_submit').text('搜索');
                $('#form1 #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
            }
    })
}
