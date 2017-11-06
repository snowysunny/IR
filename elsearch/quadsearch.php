<!DOCTYPE html>
<?php
error_reporting(0);
include 'myhead.php';
?>

<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/bootstrap-table-group-by.css">
<link rel="stylesheet" href="css/bootstrap-table-filter-control.css">
<link rel="stylesheet" href="js/bootstrap-table.css">
<script src="js/typeadhead.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-zh-CN.js"></script>
<script src="js/bootstrap-table-filter-control.js"></script>
<script src="js/bootstrap-table-filter.js"></script>

<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 shadow-common" ><!-- 最外面的框 -->
            <div class=" search-common">
                <div class="bg-color center-block" ><!-- main search -->
                    <div class="row">

                        <div class="search-top-pad"></div>
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1 col-sm-10 col-sm-offset-1 text-center">
                            <div class="form-group" >
                                <form onkeydown="if (event.keyCode == 13) {
                                            getsearch();
                                            return false;
                                        }" id="form1">
                                    <div class="input-group ">

                                        <div class="input-group-btn">
                                            <select name="querytype" id="type" class="form-control form-white" style="width: auto">
                                                <option value="0">正文</option>
                                                <option value="1">标题</option>
                                                <option value="2">标签</option>
                                                <option value="3">关键字</option>
                                                <!--<option value="4">id</option>-->
                                            </select>
                                        </div>

                                        <input type="text" name="keyword" id="strquery" autocomplete="off" class="form-control form-white" maxlength="100" placeholder="请您输入关键词">
                                        <span class="input-group-btn">
                                            <button class="btn btn-white"  id="search_submit" type="button" onclick="getsearch()" >搜索</button>
                                        </span>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1 col-sm-10 col-sm-offset-1 text-center">
                            <div class="form-group" >
                                <form onkeydown="if (event.keyCode == 13) {
                                            getsearchinresult();
                                            return false;
                                        }" id="form2">
                                    <div class="input-group ">
                                        <input type="text" name="keyword" id="strquery2" autocomplete="off" class="form-control form-white" maxlength="100" placeholder="请您输入关键词">
                                        <span class="input-group-btn">
                                            <button class="btn btn-white"  id="search_submit" type="button" onclick="getsearchinresult()" >在结果中搜索</button>
                                        </span>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="raw">
                        <div class="search-bottom-pad"></div>
                    </div>
                </div>
                <div id="tablediv">  
                    <table id="table" style="table-layout:fixed"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getsearch()
    {
        var query = $.trim($("#strquery").val());
        var type = $("#type  option:selected").attr('value');
        var data;
        console.log(query);
        $('#strquery2')[0].value = "";
        if (query == null || query == "") {
            //此处留用
        }
        $.ajax({
            url: "./Queryjson_2.php",
            data: {query: query, querytype: type},
            success: function (e) {
                $('#form1 #search_submit').attr('disabled', false); 
                $('#form2 #search_submit').attr('disabled', false);
                var e = e.replace(/^\s+|\s+$/g, "");
                e = $.parseJSON(e);
                e = e.rows;
                data = e;
                $('#table').bootstrapTable("load", data);
            },
            beforeSend: function ()
            {   //触发ajax请求开始时执行
                //$('#advanced-search-form #search_submit').text('搜索中...');
                $('#form1 #search_submit').attr('disabled', true); 
                $('#form2 #search_submit').attr('disabled', true);  //改变提交按钮上的文字并将按钮设置为不可点击
            },
            error: function (returndata)
            {
                //$('#advanced-search-form #search_submit').text('搜索');
                $('#form1 #search_submit').attr('disabled', false);
                $('#form2 #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
            }
        });
    }

    function getsearchinresult() {
        var data = new Object();
        data['querytype'] = $('#type').val();
        data['query'] = $.trim($('#strquery').val());
        data['query-content2'] = $.trim($('#strquery2').val());
        data_json = JSON.stringify(data);
        $.ajax({  
            async: false,  
            type: "post",  
            url: "Queryjson_1.php",  
            data: data_json,  
            dataType: "json",  
            contentType: "application/json; charset=utf-8",
            success: function (data) {  
                $('#form1 #search_submit').attr('disabled', false); 
                $('#form2 #search_submit').attr('disabled', false);
                data = data.rows;
                $('#table').bootstrapTable("load", data);
            } ,
                      beforeSend: function ()
            {   //触发ajax请求开始时执行
                //$('#advanced-search-form #search_submit').text('搜索中...');
                $('#form1 #search_submit').attr('disabled', true); 
                $('#form2 #search_submit').attr('disabled', true);  //改变提交按钮上的文字并将按钮设置为不可点击
            },
            error: function (returndata)
            {
                //$('#advanced-search-form #search_submit').text('搜索');
                $('#form1 #search_submit').attr('disabled', false);
                $('#form2 #search_submit').attr('disabled', false);//改变提交按钮上的文字并将按钮设置为可点击 
            }
        })  
    }


    $('#table').bootstrapTable({
        dataType: 'json',
//cardView:true,
        sidePagination: 'client',
        pagination: true,
        striped: true,
        showFilter: true,
        showColumns: true,
        sortable: false,
        paginationLoop: false,
//  groupByField:["标签"],
        filterControl: true, //是否显示所有的列
        grouyby: true,
        columns: [{
                formatter: function (value, row, index) {
                    var url = " <a href='showpage.php?id=" + row.id + "' target='_blank'>" + value + "</a>";
                    ;
                    return url;
                 },
                field: 'title',
                title: '标题',
                width: '100px',
                filterControl: 'input'
            }, {formatter:
                        function (value, row, index) {
                            if (value.length > 150)
                                         return value.substring(0, 140) + "....";
                            else
                                return value;
                         },
                field: 'content',
                title: '内容',
                width: '300px',
                filterControl: 'input'
            }, {
                field: 'pubtime',
                filterControl: 'select',
                title: '发布时间',
                width: '80px'
            }, {
                field: 'tag',
                title: '标签',
                width: '50px',
                filterControl: 'select'
            },
            {
                formatter: function (value, row, index) {
                    var url = " <a href='" + value + "'>" + "点击" + "</a> ";
                    return url;
                 },
                field: 'url',
                title: '源网站',
                width: '60px'
            }]
    });

    var $input = $("#strquery");
    $input.typeahead({
        delay: 500,
        items: 8, //最多显示个数
        source: function (query, process) {
            $.get("./test.php", {query: query}, function (e) {
                var e = e.replace(/^\s+|\s+$/g, "");
                var obj = $.parseJSON(e);
                                                        process(obj);
                                });
         }
    });
</script>
<?php
include 'myfoot.php';
?>
