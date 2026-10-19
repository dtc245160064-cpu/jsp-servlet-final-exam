<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Quan ly san pham</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"></head>
<body><main><h1>Quản lý sản phẩm</h1><p><a class="btn" href="${pageContext.request.contextPath}/products?action=new">+ Thêm sản phẩm</a></p>
<table><thead><tr><th>ID</th><th>Tên sản phẩm</th><th>Giá (VNĐ)</th><th>Số lượng</th><th>Thao tác</th></tr></thead><tbody>
<c:forEach var="p" items="${products}"><tr><td>${p.id}</td><td><c:out value="${p.name}"/></td><td>${p.price}</td><td>${p.quantity}</td>
<td><a href="${pageContext.request.contextPath}/products?action=edit&amp;id=${p.id}">Sửa</a>
<form class="inline" method="post" action="${pageContext.request.contextPath}/products" onsubmit="return confirm('Bạn chắc chắn muốn xóa sản phẩm này?');"><input type="hidden" name="action" value="delete"><input type="hidden" name="id" value="${p.id}"><button class="danger" type="submit">Xóa</button></form></td></tr></c:forEach>
<c:if test="${empty products}"><tr><td colspan="5">Chưa có sản phẩm nào.</td></tr></c:if>
</tbody></table></main></body></html>