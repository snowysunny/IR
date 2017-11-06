/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function genhtml(i) {
    return '<div id="query-' + i + '" class="row querymargin"><div class="form-inline" ><div class="form-group"><div class="input-group"><div class="input-group-btn"><select name="querytype" id="type" class="form-control form-white" style="width: auto;"><option value="and">必须包含</option><option value="or">可以包含</option><option value="not">不包含</option></select></div><div class="input-group-btn"><select name="queryfield" id="field" class="form-control form-white" style="width: auto;"><option value="0">正文</option><option value="1">标题</option><option value="2">标签</option><option value="3">关键字</option><!--<option value="4">id</option>--></select></div><input type="text" name="keyword" id="strquery" autocomplete="off" class="form-control form-white min-width" maxlength="100" placeholder="请您输入关键词"></div></div></div></div>'
}
function addquery() {
    html = genhtml(count);
    $(html).appendTo('#advanced-search');
    count++;
    $('#btn-minus').removeAttr('disabled');
    if (count >= 6) {
        $('#btn-plus').attr('disabled', 'disabled');
    }
}
function delquery() {
    count--;
    $('#query-' + count).remove();
    $('#btn-plus').removeAttr('disabled');
    if (count <= 1) {
        $('#btn-minus').attr('disabled', 'disabled');
    }
}
var mincount = 1;
var maxcount = 6;
var count = 3;


function getsearch() {
    var data = new Object();
    var total = 0;
    data['rows'] = new Array();
    for (var i = 0; i < count; i++) {
        var querydata = new Object();
        querydata['keyword'] = $.trim($('#query-' + i + ' input#strquery').val());
        if (querydata['keyword'] == null || querydata['keyword'] == "") {
            if (i == 0) {
                alert("第一行必须输入关键字");
                return false;
            }
            continue;
        }

        if (i == 0) {
            querydata['querytype'] = 'and'
        } else {
            querydata['querytype'] = $('#query-' + i + ' select#type option:selected').attr('value');
        }
        querydata['queryfield'] = $('#query-' + i + ' select#field option:selected').attr('value');
        data['rows'][total] = querydata;
        total++;
    }
    data['total'] = total;
    data['usertype'] = $('#usertype').val();
    data['timestart'] = $("#datetimeStart").val();
    data['timesend'] = $("#datetimeEnd").val();
    data_json = JSON.stringify(data);
    console.log(data_json);

    $.ajax({  
            async: true,  
            type: "post",  
            url: "Queryjson.php",  
            data: data_json,  
            dataType: "json",  
                //timeout: 5000,
                contentType: "application/json; charset=utf-8",
                                  beforeSend: function ()
                {   //触发ajax请求开始时执行
                    //$('#advanced-search-form #search_submit').text('搜索中...');
                    $('#advanced-search-form #search_submit').attr('disabled', true);  //改变提交按钮上的文字并将按钮设置为不可点击
                },
            success: function (data) {  
                    //$('#advanced-search-form #search_submit').text('搜索');
                    $('#advanced-search-form #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
                    data = data.rows;
                    $('#table').bootstrapTable("load", data);
            },

                error: function(returndata)
                {
                    //$('#advanced-search-form #search_submit').text('搜索');
                    $('#advanced-search-form #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
                }
        
    })  
}


$(document).ready(function () {
    $("#datetimeStart").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
    }).on("click", function () {
        $("#datetimeStart").datetimepicker("setEndDate", $("#datetimeEnd").val())
    });
    $("#datetimeEnd").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
    }).on("click", function () {
        $("#datetimeEnd").datetimepicker("setStartDate", $("#datetimeStart").val())
    });
    $('.selectpicker').selectpicker({
        noneSelectedText: "所有来源",
        style: "btn-white"
    });



    count = 3;
    for (var i = 1; i < count; i++) {
        html = genhtml(i);
        $(html).appendTo('#advanced-search');
    }
    $('#btn-plus').removeAttr('disabled');
    $('#btn-minus').removeAttr('disabled');


    $('#btn-plus').click(function () {
        if (count) {
            addquery();
        } else {
            alert("发生错误，请重新载入页面！");
        }
    });

    $('#btn-minus').click(function () {
        if (count) {
            delquery();
        } else {
            alert("发生错误，请重新载入页面！");
        }
    });
});






