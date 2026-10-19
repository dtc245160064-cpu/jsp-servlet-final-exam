<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Biểu mẫu sản phẩm</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"></head>
<body><main><h1><c:choose><c:when test="${mode eq 'edit'}">Sửa sản phẩm</c:when><c:otherwise>Thêm sản phẩm</c:otherwise></c:choose></h1>
<form method="post" action="${pageContext.request.contextPath}/products">
<input type="hidden" name="action" value="${mode eq 'edit' ? 'update' : 'create'}">
<c:if test="${mode eq 'edit'}"><input type="hidden" name="id" value="${product.id}"></c:if>
<label>Tên sản phẩm<input type="text" name="name" value="<c:out value='${product.name}'/>" maxlength="255" required></label>
<label>Giá (VNĐ)<input type="number" name="price" min="0" max="9999999999.99" step="0.01" value="${product.price}" required></label>
<label>Số lượng<input type="number" name="quantity" min="0" step="1" value="${product.quantity}" required></label>
<button class="btn" type="submit">Lưu sản phẩm</button> <a href="${pageContext.request.contextPath}/products">Hủy</a>
</form></main></body></html>