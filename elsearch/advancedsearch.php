<!DOCTYPE html>
<?php
error_reporting(0);
include 'myhead.php';
?>

<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/bootstrap-table-group-by.css">
<link rel="stylesheet" href="css/bootstrap-table-filter-control.css">
<link rel="stylesheet" href="js/bootstrap-table.css">
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="css/bootstrap-select.min.css" rel="stylesheet">
<script src="js/typeadhead.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-zh-CN.js"></script>
<script src="js/bootstrap-table-filter-control.js"></script>
<script src="js/bootstrap-table-filter.js"></script>
<script src="js/advancedsearch.js"></script>
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="js/bootstrap-select.min.js" ></script>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 shadow-common" ><!-- 最外面的框 -->
            <div class=" search-common">
                <div class="bg-color center-block" ><!-- main search -->
                    <div class="row">

                        <div class="search-top-pad"></div>
                    </div>
                    <div class="row"><!-- main search -->
                        <div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1 col-sm-10 col-sm-offset-1 text-center">
                            <form id="advanced-search-form" onkeydown="if(event.keyCode==13){getsearch();return false;}"><!-- form -->

                                <div id="advanced-search" class="row"><!-- 增删条件的div -->
                                    <div id="query-0" class="row querymargin">
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <button type="button" class="btn btn-white" id="btn-plus">+</button>
                                                <button type="button" class="btn btn-white" id="btn-minus">-</button>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <select name="queryfield" id="field" class="form-control form-white" style="width: auto">
                                                            <option value="0">正文</option>
                                                            <option value="1">标题</option>
                                                            <option value="2">标签</option>
                                                            <option value="3">关键字</option>
                                                            <!--<option value="4">id</option>-->
                                                        </select>
                                                    </div>
                                                    <input type="text" name="keyword" id="strquery" autocomplete="off" class="form-control form-white min-width" maxlength="100" placeholder="请您输入关键词">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="other"  class="row"><!-- 其他搜索选项 -->
                                    <div class="row querymargin">
                                        <div class="form-inline">
                                            <div class="form-group chr-white txtmargin">
                                                <label> 来源：</label>
                                                <select id="usertype" name="usertype" class="selectpicker show-tick" multiple data-live-search="false">
                                                    <option value="weibo">微博</option>
                                                    <option value="weixin">微信</option>
                                                    <!--<option value="wenshu">文书网</option>-->
                                                    <option value="common">互联网</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-inline">

                                            <div class="form-group txtmargin">
                                                <label class="chr-white">发布时间:</label>
                                                <input type="text" id="datetimeStart" readonly class="form-control form-white form-datetime">
                                                <label class="chr-white">-</label>
                                                <input type="text" id="datetimeEnd" readonly class="form-control form-white form-datetime">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="querymargin">
                                    <button class="btn btn-default btn-white" style="padding-left: 30px; padding-right: 30px;" id="search_submit" type="button" onclick="getsearch()" >搜索</button>
                                </div>

                            </form>


                        </div>
                    </div>

                    <div class="row">
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

<script>
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
