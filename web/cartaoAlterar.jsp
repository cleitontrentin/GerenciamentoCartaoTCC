<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Cartão</h1>
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

<c:set var="cartao" value="${cartao}" />
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-edit fa-fw"></i> Alteração de Cartão
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form role="form" id="formAtual" name="formAtual">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Nome</label>
                                <input class="form-control" placeholder="Informe o nome" type="text" id="txtNome" name="txtNome" value="${cartao.nome}"> 
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Carência</label>
                                <input class="form-control" placeholder="Informe a carência" type="text" id="txtCarencia" name="txtCarencia" value="${cartao.carencia}">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>taxa</label>
                                <input class="form-control" placeholder="Informe a taxa de juros" type="text" id="txtTaxa" name="txtTaxa" value="${cartao.taxa}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <input type="hidden" name="txtId" id="txtId" value="${cartao.id}" >
                            <input type="hidden" name="txtObjeto" id="txtObjeto" value="cartao" >
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

    });

    $("#btnEnviar").click(function() {
        $("#conteudoForm").load("servlet", {
            //variaveis de controle
            txtObjeto: document.forms["formAtual"].elements["txtObjeto"].value
            , txtMetodo: document.forms["formAtual"].elements["txtMetodo"].value
                    //variaveis para o objeto
            , txtId: document.forms["formAtual"].elements["txtId"].value
            , txtNome: document.forms["formAtual"].elements["txtNome"].value
            , txtCarencia: document.forms["formAtual"].elements["txtCarencia"].value
            , txtTaxa: document.forms["formAtual"].elements["txtTaxa"].value
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    
</script>
