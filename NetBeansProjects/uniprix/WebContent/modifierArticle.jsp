<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <label>Nom</label>			<input name='nomArt' type='text' value='${article.nom}'/><br/>
        <label>Description</label>	
        <textarea style="resize:none;" cols="60" rows="4" name="descArt">${article.description}</textarea><br/>
        <label>Image</label>		<input name='imgArt' type='text' value='${article.image}'/><br/>
        <label>Prix</label>			<input name='prixArt' type='text' value='${article.prix}'/><br/>
        <input type='hidden' name='idArt' value='${article.articleId}'/>
        <input type='hidden' name='idProd' value='${idproduit}'/>
        <input type='hidden' name='idCat' value='${idCat}'/>${idCat}
        <input type='submit'/>
    </form>		

    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>