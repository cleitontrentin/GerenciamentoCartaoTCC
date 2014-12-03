<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert ${tipo} fade in" role="alert">

    <h4 align="center">${titulo}</h4>
    <hr>
    <p>${texto}</p>
    <p align="center">
        <button type="button" class="btn btn-default" id="btnOk">Ok</button>
    </p>	
</div>
<script type="text/javascript">
        $("#btnOk").click(function() {
    <c:if test="${objeto == ''}">
            window.location = "${metodo}.jsp";
    </c:if>
    <c:if test="${objeto != ''}">
            $("#conteudoForm").load("servlet", {
                //variaveis de controle
                txtObjeto: '${objeto}'
                , txtMetodo: '${metodo}'
            }, function(responseTxt, statusTxt, xhr) {
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
            });
        
    </c:if>
        });
    
</script>
