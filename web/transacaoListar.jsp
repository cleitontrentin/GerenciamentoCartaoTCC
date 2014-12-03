<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Transação</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-edit fa-fw"></i> Lista de Transação
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-funcionarios">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Cartão</th>
                                <th>Valor</th>
                                <th>Data</th>
                                <th width="160px">Acao</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="transacao" items="${transacoes}">


                                <tr class="odd gradeX">
                                    <td>${transacao.id}</td>
                                    <td>${transacao.cartaoNome}</td>
                                    <td>${transacao.valor}</td>
                                    <td>${transacao.data}</td>
                                    <td>
                                        <a href="#" class="btnAlterar" id="${transacao.id}">
                                            <i class="fa fa-edit fa-fw"></i>Detalhe
                                        </a>
                                        &nbsp;
                                        <a href="#" class="btnExcluir" id="${transacao.id}">
                                            <i class="fa fa-trash-o fa-fw"></i>Excluir
                                        </a>                        
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel-body -->
    </div>
</div>
<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- Page-Level Demo Scripts - Tables - Use for reference -->

<script>
 $(document).ready(function() {
        $('#dataTables-funcionarios').dataTable();
    });

    $(".btnAlterar").click(function() {
        $("#conteudoForm").load("servlet", {
            //variaveis de controle
            txtObjeto: 'transacao'
            , txtMetodo: 'detalhe'
                    //variaveis para o objeto
            , txtId: this.id
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });

    $(".btnExcluir").click(function() {
        if (confirm('Deseja realmente excluir o registro?')) {
            $("#conteudoForm").load("servlet", {
                //variaveis de controle
                txtObjeto: 'transacao'
                , txtMetodo: 'deletar'
                        //variaveis para o objeto
                , txtId: this.id
                , txtCartao: document.forms["formAtual"].elements["txtCartao"].value
            }, function(responseTxt, statusTxt, xhr) {
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
            });
        }
    });
   

</script>
