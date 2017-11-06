<!DOCTYPE html>
<?php
error_reporting(0);
include 'myhead.php';
?>
<link rel="stylesheet" href="js/bootstrap-table.css">
<link href="css/dhtmlxchart.css" rel="stylesheet" type="text/css" />
<link href="css/roboto.css" rel="stylesheet" type="text/css">
<script src="js/echarts.min.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="js/bootstrap-table-zh-CN.js"></script>
<style>
    .mymain{
        margin-top: 40px;
        min-height:100%;
        height:100%;
        border-bottom: 40px;
    }
</style>
<script>
    $(document).ready(function () {
        $("#myNav").affix({
            offset: {
                top: 125
            }
        });
    });
    function checksize() {
        $('.mymain').css('height', $('#right').css('height'));
        $('#left').css('height', "100%");
        $('#left-right').css('height', "100%");
        $('#maincontainer').css('height', "100%");
    }
    $(window).resize(function () {
        checksize();
    });
</script>

<!--<script src="js/dhtmlxchart.js"></script>-->
<!--<script src="js/showchart.js"></script>-->
<div id="maincontainer" class="container-fluid" style="height: 100%">
    <div id="left-right" class="row" style="height: 100%">
        <!--        <div id="left" class="col-lg-2 col-md-2 col-sm-2 col-xs-1 bg-color" style="height: 100%; " > 左侧导航栏 
                    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px">
                        <li><a style="color: white" href="Usermanager_userlist.php">用户管理</a></li>
                         这里的active属性就是使得所在的li的背景色变为蓝色
                        <li><a style="color: white" href="Usermanager_datawatcher.php">数据监控</a></li>
                        <li><a style="color: white" href="Usermanager_upload.php">文件上传</a></li>
                        <h5 style="color: white">&nbsp;&nbsp;&nbsp;&nbsp;爬虫设置</h5>
                        <li><a style="color: white" href="Usermanager_urlset.php">&nbsp;&nbsp;·网址</a></li>
                        <li><a style="color: white" href="Usermanager_kwdset.php">&nbsp;&nbsp;·关键字</a></li>
                    </ul>
                </div>-->
        <?PHP
        include 'Usermanager_left.php';
        ?>
        <div id="right" class="col-lg-10 col-md-10 col-sm-10 " style="height: auto; min-height: 540px">
            <div class="text-center" style="margin-top: 50px">
                <div align="center">
                    <div id="chartbox" style="width:600px;height:250px;border:1px solid #c0c0c0;margin: 20px auto;"></div>
                    <div id="pie" style="width:400px;height:250px;border:1px solid #c0c0c0;margin: 20px auto;"></div>
                </div>
                <script type='text/javascript'>
    function GetDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
//	var y = dd.getFullYear();
        var m = dd.getMonth() + 1;//获取当前月份的日期
        var d = dd.getDate();
//	return y+"-"+m+"-"+d;
        return m + "-" + d;
    }

    var dataset;
    var month_dataset;
    var obj;
    var rows

    function ShowChart() {
        var strurl = "http://116.62.148.121:8080/webserver/mongodb";
        var bar = document.getElementById('chartbox');
        var pie = document.getElementById('pie');
        var myChart = echarts.init(bar);
        var myPieChart = echarts.init(pie);
        // alert(strurl);
        $.get(strurl, function (data) {
            obj = data.replace(/^\s+|\s+$/g, "");
            obj = $.parseJSON(obj);
            rows = obj.rows;
            //	alert(rows[1].num);
            //	alert(obj.total);
            //  alert(obj.WeiboNum);
            //  document.write(obj.CommonNum);
            
            chartoption = {
    title: {
        text: '日爬取量',
        x: 'center'
    },
    tooltip: {
        trigger: 'axis',
    },
    legend: {
        icon: 'rect',
        itemWidth: 14,
        itemHeight: 5,
        itemGap: 13,
        data: ['微博','微信','互联网'],
        right: '4%',
        textStyle: {
            fontSize: 12
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: [GetDateStr(-6), GetDateStr(-5), GetDateStr(-4), GetDateStr(-3), GetDateStr(-2), GetDateStr(-1), GetDateStr(0)]
    }],
    yAxis: [{
        type: 'value',
        name: '爬取条数',
        axisTick: {
            show: false
        },
        axisLabel: {
            margin: 10,
            textStyle: {
                fontSize: 14
            }
        },
    }],
    series: [{
        name: '微博',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
            normal: {
                width: 1
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(40, 40, 255, 0.8)'
                }, {
                    offset: 0.8,
                    color: 'rgba(40, 40, 255,  0.1)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {
                color: 'rgb(40, 40, 255)',
                borderColor: 'rgba(40,40,241,0.27)',
                borderWidth: 12

            }
        },
        data: [obj.weiborows[0].num, obj.weiborows[1].num, obj.weiborows[2].num, obj.weiborows[3].num, obj.weiborows[4].num, obj.weiborows[5].num, obj.weiborows[6].num]
    },{
        name: '微信',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
            normal: {
                width: 1
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(128, 128, 192, 0.8)'
                }, {
                    offset: 0.8,
                    color: 'rgba(128, 128, 192,  0.1)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {
                color: 'rgb(128, 128, 192)',
                borderColor: 'rgba(128, 128, 192,0.27)',
                borderWidth: 12

            }
        },
        data: [obj.weixinrows[0].num, obj.weixinrows[1].num, obj.weixinrows[2].num, obj.weixinrows[3].num, obj.weixinrows[4].num, obj.weixinrows[5].num, obj.weixinrows[6].num]
    },{
        name: '互联网',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
            normal: {
                width: 1
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(00, 156, 255, 0.8)'
                }, {
                    offset: 0.8,
                    color: 'rgba(00, 156, 255,  0.1)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {
                color: 'rgb(00, 156, 255)',
                borderColor: 'rgba(73,184,241,0.27)',
                borderWidth: 12

            }
        },
        data: [obj.rows[0].num, obj.rows[1].num, obj.rows[2].num, obj.rows[3].num, obj.rows[4].num, obj.rows[5].num, obj.rows[6].num]
    }]};
            
            
//            chartoption = {
//                title: {
//                    text: '日爬取量',
//                    x: 'center'
//                },
//                //color: ['#3398DB'],
//                color: ['#009cff'],
//            
//                tooltip: {
//                    trigger: 'axis',
//                    axisPointer: {// 坐标轴指示器，坐标轴触发有效
//                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//                    }
//                },
//                grid: {
//                    left: '3%',
//                    right: '4%',
//                    bottom: '3%',
//                    containLabel: true
//                },
//                xAxis: [
//                    {
//                        type: 'category',
//                        data: [GetDateStr(-6), GetDateStr(-5), GetDateStr(-4), GetDateStr(-3), GetDateStr(-2), GetDateStr(-1), GetDateStr(0)],
//                        axisTick: {
//                            alignWithLabel: true
//                        }
//                    }
//                ],
//                yAxis: [
//                    {
//                        type: 'value'
//                    }
//                ],
//                series: [
//                    {
//                        name: '爬取量',
//                        type: 'bar',
//                        barWidth: '60%',
//                        data: [rows[0].num, rows[1].num, rows[2].num, rows[3].num, rows[4].num, rows[5].num, rows[6].num]
//                    }
//                ]
//            };
            myChart.setOption(chartoption);

            pieoption = {
                title: {
                    text: '数据来源统计',
                    x: 'center'
                },
                color:['#2828ff','#8080c0','#009cff','#ff44ff'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['微博', '微信', '互联网']
                },
                series: [
                    {
                        name: '来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: obj.WeiboNum, name: '微博'},
                            {value: obj.WeixinNum, name: '微信'},
                            {value: obj.CommonNum, name: '互联网'},
//                            {value: obj.WenshuNum, name: '文书网'}
//                            {value: 1000, name: '微博'},
//                            {value: 1000, name: '微信'},
//                            {value: 1000, name: '互联网'},
//                            {value: 1000, name: '文书网'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myPieChart.setOption(pieoption);

        });

    }




    ShowChart();
                </script>

            </div>
        </div>
    </div>
</div>
<?php
include 'myfoot.php';
?>
