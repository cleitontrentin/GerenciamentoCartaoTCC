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

<c:set var="transacao" value="${transacao}" />
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-edit fa-fw"></i> Alterar a Transação
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form role="form" id="formAtual" name="formAtual">
                    <div class="row">
                        <div class="col-lg-12" id="comboCartao">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>valor</label>
                                <input class="form-control" placeholder="Informe o valor" type="text" id="txtValor" name="txtValor" value="${transacao.valor}"> 
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Data</label>
                                <input class="form-control" placeholder="Informe a data" type="text" id="txtData" name="txtData" value="${transacao.data}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <input type="hidden" name="txtId" id="txtId" value="${transacao.id}" >
                            <input type="hidden" name="txtUsuario" id="txtUsuario" value="${sessionScope.usuarioLogado.id}" >
                            <input type="hidden" name="txtObjeto" id="txtObjeto" value="transacao" >
                            <input type="hidden" name="txtMetodo" id="txtMetodo" value="alterar">
                            <button tabindex="5" type="button" class="btn btn-default" id="btnEnviar">Salvar</button>
                        </div>
                    </div>
                </form>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->



<script type="text/javascript">
    $(document).ready(function() {
        $("#txtData").datepicker({
            format: 'dd/mm/yyyy'
        });
        loadComboCartao();
    
    function loadComboCartao() {
        $("#comboCartao").load("servlet", {
            //variaveis de controle
            txtObjeto: 'cartao'
            , txtMetodo: 'combo'
            //variaveis para o objeto
            , txtId: '${transacao.cartaoId}'
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    }
    });
    $("#btnEnviar").click(function() {
        $("#conteudoForm").load("servlet", {
            //variaveis de controle
            txtObjeto: document.forms["formAtual"].elements["txtObjeto"].value
            , txtMetodo: document.forms["formAtual"].elements["txtMetodo"].value
            //variaveis para o objeto
            , txtId: document.forms["formAtual"].elements["txtId"].value
            , txtUsuario: document.forms["formAtual"].elements["txtUsuario"].value
            , txtValor: document.forms["formAtual"].elements["txtValor"].value
            , txtData: document.forms["formAtual"].elements["txtData"].value
            , txtCartao: document.forms["formAtual"].elements["txtCartao"].value
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });

</script>
