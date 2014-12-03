<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Cartao</h1>
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
                <i class="fa fa-edit fa-fw"></i> Cadastro de Cartão
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form role="form" id="formAtual" name="formAtual">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Nome</label>
                                <input class="form-control" placeholder="Informe o nome" type="text" id="txtNome" name="txtNome">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Carência</label>
                                <input class="form-control" placeholder="Informe o carencia" type="text" id="txtCarencia" name="txtCarencia">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Taxa</label>
                                <input class="form-control" placeholder="Informe o taxa" type="text" id="txtTaxa" name="txtTaxa">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <input type="hidden" name="txtObjeto" id="txtObjeto" value="cartao" >
                            <input type="hidden" name="txtMetodo" id="txtMetodo" value="salvar">
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

<div class="row">
    <div class="col-lg-12">
        <div id="listacartao"></div>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->


<script type="text/javascript">
     $(document).ready(function() {
        $("#listacartao").load("servlet", {
            //variaveis de controle
            txtObjeto: 'cartao'
            , txtMetodo: 'listar'
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });

    });

    $("#btnEnviar").click(function() {
        $("#conteudoForm").load("servlet", {
            //variaveis de controle
            txtObjeto: document.forms["formAtual"].elements["txtObjeto"].value
            , txtMetodo: document.forms["formAtual"].elements["txtMetodo"].value
                    //variaveis para o objeto
            , txtNome: document.forms["formAtual"].elements["txtNome"].value
            , txtCarencia: document.forms["formAtual"].elements["txtCarencia"].value
            , txtTaxa: document.forms["formAtual"].elements["txtTaxa"].value
        }, function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });


</script>
