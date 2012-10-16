<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        List Article du jour
    </div>

    <c:forEach var="article" items="${listArticleDuJour}">
        <div class="product_box">
            <img src="Images/Articles/${article.image}" alt="" title="" class="prod_image">
            <div class="product_details">
                <div class="prod_title">${article.nom}</div>
                <p>
                    ${article.description}
                </p>
                <p class="price">Prix: <span class="price">${article.prix}</span> euro</p>
                <!-- <a href="details.html" class="details"><img src="images/details.gif" alt="" title="" border="0"></a> -->
                <form action="PanierServlet" method="Get">
                    <!-- 				id article -->
                    <input type="hidden" name="articleId"
                           value="${article.articleId}">
                    <input type="hidden" name="articles"
                           value="<%=request.getParameter("articles")%>">
                    <!-- 				quantité -->
                    <input type="hidden" name="quantite" value="1">
                    <input id='submit"+ligne.getArticle().getArticleId()+"' type="submit" value="ajouter" 
                           onClick="getIdArticle(${article.articleId})">
                </form>
            </div>
        </div>
    </c:forEach>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>