<?php
error_reporting(0);
session_start();
?>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/docs.min.css" rel="stylesheet">
        <link href="css/head.css" rel="stylesheet">

        <script src="js/jquery.min.js" ></script>
        <script src="js/bootstrap.js"></script>
        <link rel="stylesheet" href="css/common.css">
        <title>ICLMS法治国情数据库</title>
        <style>
            .navbar-default{
                height: 40px;
            }
            html{
                height: 100%;
            }
            body{

                height: 90%;
            }
            .mymain{
                margin-top: 60px;
                min-height:100%;
            }

            .box-shadow-2{
                box-shadow: 0px 10px 10px #e1dcdc;
            }
        </style>

    </head>
    <body>
        <nav id="head" class="navbar navbar-default navbar-fixed-top box-shadow-2"  role="navigation" style="padding-left:50px;padding-right:50px">
            <div class="container-fluid">
                <div class="row">
                    <div class="navbar-header ">            
                        <a class="navbar-brand" href="index.php">
                            <img alt="icon" src="icon/LittleLogo.jpg">
                        </a>
                    </div>
                    <div class=" collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li>

                                <button type="button" class="btn btn-default btn-lg navbar-btn dropdown-toggle " style="border:none;padding-bottom: 6px " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true" ></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="index.php">首页</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="advancedsearch.php">高级检索</a></li>
                                    <li><a href="quadsearch.php">二次检索</a></li>
                                </ul>

                            </li>
                        </ul>     
                    </div>
                </div>
            </div>
        </nav>
        <div class="mymain">



