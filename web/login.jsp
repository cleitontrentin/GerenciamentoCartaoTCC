    <!doctype html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Controle de Cartão de Crédito</title>

        <!-- Core CSS - Include with every page -->
        <link href="include/css/bootstrap.min.css" rel="stylesheet">
        <link href="include/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="include/css/datepicker.css" rel="stylesheet">
        <!-- Page-Level Plugin CSS - Tables -->
        <link href="include/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
        <!-- Page-Level Plugin CSS - Dashboard -->
        <link href="include/css/plugins/timeline/timeline.css" rel="stylesheet">
        <!-- SB Admin CSS - Include with every page -->
        <link href="include/css/sb-admin.css" rel="stylesheet">

        <!-- Fav and touch icons -->
        <link rel="shortcut icon" href="include/img/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="include/img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="include/img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="include/img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="include/img/apple-touch-icon-57-precomposed.png">        
    </head>

    <body>
        <div id="conteudoForm">
            <div class="row"> 
                <div class="col-lg-3" ></div>
                <div class="col-lg-6" >
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-edit fa-fw"></i> login
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form role="form" id="formAtual" name="formAtual">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Login</label>
                                            <input class="form-control" placeholder="Informe o login" type="text" id="txtLogin" name="txtLogin">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Senha</label>
                                            <input class="form-control" placeholder="Informe o senha" type="text" id="txtSenha" name="txtSenha">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12" align="right">
                                        <input type="hidden" name="txtObjeto" id="txtObjeto" value="usuario" >
                                        <input type="hidden" name="txtMetodo" id="txtMetodo" value="logon">
                                        <button tabindex="5" type="button" class="btn btn-default" id="btnEnviar">Acessar</button>
                                    </div>
                                </div>
                            </form>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div>
                <div class="col-lg-3" ></div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>

        <!-- Core Scripts - Include with every page -->
        <script src="include/js/jquery-1.10.2.js"></script>
        <script src="include/js/bootstrap.min.js"></script>
        <script src="include/js/plugins/metisMenu/jquery.metisMenu.js"></script>
        <script src="include/js/bootstrap-datepicker.js"></script>

        <!-- Page-Level Plugin Scripts - Dashboard -->
        <script src="include/js/plugins/morris/raphael-2.1.0.min.js"></script>

        <!-- Page-Level Plugin Scripts - Tables -->
        <script src="include/js/plugins/dataTables/jquery.dataTables.js"></script>
        <script src="include/js/plugins/dataTables/dataTables.bootstrap.js"></script>

        <!-- SB Admin Scripts - Include with every page -->
        <script src="include/js/sb-admin.js"></script>

        <!-- Scripts de funcionario da pagina -->
        <script type="text/javascript">
            $("#btnEnviar").click(function() {
                $("#conteudoForm").load("servlet", {
                    //variaveis de controle
                    txtObjeto: document.forms["formAtual"].elements["txtObjeto"].value
                    , txtMetodo: document.forms["formAtual"].elements["txtMetodo"].value
                            //variaveis para o objeto
                    , txtLogin: document.forms["formAtual"].elements["txtLogin"].value
                    , txtSenha: document.forms["formAtual"].elements["txtSenha"].value
                }, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            });

        </script>


    </body>

</html>
