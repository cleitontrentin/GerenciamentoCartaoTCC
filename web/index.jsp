<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div id="wrapper">

            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="admin">Gestão de Cartão</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a class="text-center" href="principal"><i class="fa fa-home fa-lg"></i>
                        </a>                
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-fw"></i> ${sessionScope.usuarioLogado.nome} <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            
                            <c:if test="${sessionScope.SessaoLogado == true}">
                                <li><a href="#" id="detalheUsuario"><i class="fa fa-male fa-fw"></i> Meu Perfil</a></li>
                            </c:if>

                            <c:if test="${sessionScope.SessaoLogado != true}">
                                <li><a href="#" id="login"><i class="fa fa-male fa-fw"></i> login</a></li>
                             </c:if>

                            <li class="divider"></li>
                            <li><a href="#" id="logoff"><i class="fa fa-sign-out fa-fw"></i> Sair </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default navbar-static-side" role="navigation">
                    <div class="sidebar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="index.jsp"><i class="fa fa-home fa-fw"></i> Principal </a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-edit fa-fw"></i> Cadastros <span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="#" class="linkForm" id="usuario"><i class="fa fa-files-o fa-fw"></i> Usuário</a></li>
                                    <li><a href="#" class="linkForm" id="cartao"><i class="fa fa-files-o fa-fw"></i> Bandera </a></li>
                                    <li><a href="#" class="linkForm" id="transacao"><i class="fa fa-files-o fa-fw"></i> Transação</a></li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-print fa-fw"></i> Relatórios <span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    
                                     <li><a href="#" class="linkLista" id="cartao"><i class="fa fa-file-text-o fa-fw"></i> Vendas dos Cartão </a></li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                        </ul>
                        <!-- /#side-menu -->
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <br>
                <div id="conteudoForm">
                </div>
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

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
            $(".linkForm").click(function() {
                $("#conteudoForm").load("servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'principal'
                }, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            });

            $(".linkLista").click(function() {
                window.open("servlet?txtObjeto=" +  this.id + "&txtMetodo=imprimir");
            });

            $("#logoff").click(function() {
                $("#conteudoForm").load("servlet", {
                    //variaveis de controle
                    txtObjeto: 'usuario'
                    , txtMetodo: 'logoff'
                }, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            });


            $("#detalheUsuario").click(function() {
                $("#conteudoForm").load("servlet", {
                    //variaveis de controle
                    txtObjeto: 'usuario'
                    , txtMetodo: 'detalhe'
                    , txtId: '${sessionScope.usuarioLogado.id}'
                }, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            });

            $("#login").click(function() {
                $("#conteudoForm").load("servlet", {
                    //variaveis de controle
                    txtObjeto: ''
                    , txtMetodo: 'login'
                }, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            });

        </script>


    </body>

</html>
