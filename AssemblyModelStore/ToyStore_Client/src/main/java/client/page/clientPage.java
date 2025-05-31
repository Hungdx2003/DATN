package client.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.objects.ImageObject;
import client.cart.cartModel;
import client.cartItem.cartItemModel;
import client.discount.discountModel;
import client.image.imageModel;
import client.news.newsModel;
import client.objects.CartObject;
import client.objects.DiscountObject;
import client.objects.NewsObject;
import client.objects.OrderObject;
import client.objects.ProductCategory;
import client.objects.UserObject;
import client.order.orderModel;
import client.orderDetail.orderDetailModel;
import client.product.productModel;
import client.productCategory.categoryModel;
import client.productReview.productReviewModel;
import client.viewModel.ProductReviewViewModel;
import client.viewModel.cartItemViewModel;
import client.viewModel.orderDetailView;
import client.viewModel.orderView;
import client.viewModel.productViewModel;

/**
 * Servlet implementation class clientPage
 */
@WebServlet("/page")
public class clientPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private categoryModel cm;
    private productModel pm;
    private cartItemModel cim;
    private orderModel om;
    private orderDetailModel odm;
    private cartModel cam;
    private newsModel nm;
    private productReviewModel prm;
    private discountModel dm;
    private imageModel im;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cm = new categoryModel();
        pm = new productModel();
        cim = new cartItemModel();
        om=new orderModel();
        odm=new orderDetailModel();
        cam=new cartModel();
        nm=new newsModel();
        prm=new productReviewModel();
        dm=new discountModel();
        im=new imageModel();
        
        ArrayList<ProductCategory> categoriesCache = cm.getProductCategory(null, 0, (byte)50);
        getServletContext().setAttribute("categories", categoriesCache);
        ArrayList<String> brandCache=pm.getBrand();
        getServletContext().setAttribute("brands", brandCache);
    }
    
    public clientPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String view = request.getParameter("view");
        String contentPage;
        String pageTitle;
        
        HttpSession session=request.getSession();
        UserObject user=(UserObject) session.getAttribute("logUser");
        request.setAttribute("user", user);
        
        int userId = -1;
        if (user != null) {
            userId = user.getUser_id();
        }
        Integer totalItemSession = (Integer) session.getAttribute("totalCartItem");
        int totalItem;
        if (user!=null) {
        	if (totalItemSession == null) {
                totalItem = cim.getTotalCartItem(userId);
                session.setAttribute("totalCartItem", totalItem);
            } else {
                totalItem = totalItemSession;
            }
		}else {
			totalItem=0;
		}
        request.setAttribute("totalCartItem", totalItem);
    	
    	@SuppressWarnings("unchecked")
    	ArrayList<ProductCategory> pc = (ArrayList<ProductCategory>) getServletContext().getAttribute("categories");
        request.setAttribute("categories", pc);
        
        @SuppressWarnings("unchecked")
		ArrayList<String> brand=(ArrayList<String>) getServletContext().getAttribute("brands");
        request.setAttribute("brand", brand);
        
        if ("home".equals(view)) {
        	ArrayList<productViewModel> list_new=pm.getNewProduct();
        	ArrayList<productViewModel> list_best=pm.getBestSeller();
        	
        	ArrayList<productViewModel> list_sale=pm.getSaleProducts(null,0,(byte)5);
        	
        	request.setAttribute("newProduct", list_new);
        	request.setAttribute("bestSeller", list_best);
        	request.setAttribute("listSale", list_sale);
        	
        	ArrayList<DiscountObject> discounts=dm.getValidDiscount();
        	request.setAttribute("discounts", discounts);
        	
        	ArrayList<NewsObject> hotNews=nm.getHotNews();
            request.setAttribute("posts", hotNews);
        	
            ArrayList<ImageObject> list_banner=im.getImages("banner");
        	request.setAttribute("banner", list_banner);
        	
        	ArrayList<ImageObject> list_promo=im.getImages("promotion");
        	request.setAttribute("promo", list_promo);
            
            contentPage = "index.jsp";
            pageTitle = "Trang chủ";
        } else if ("layout".equals(view)) {
            contentPage = "layout.jsp";
            pageTitle = "HiStore";
        }else if ("login".equals(view)) {
            contentPage = "login.jsp";
            pageTitle = "HiStore";
        }else if ("shop".equals(view)) {	
        	int currentPage = getPageParam(request);
            int itemsPerPage = 9;
            int totalItems = pm.GetTotalProducts();
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;

            ArrayList<productViewModel> po = pm.getProduct(null, offset, (byte) itemsPerPage);

            List<Integer> paginationData = generatePagination(currentPage, total_pages);
            
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
            request.setAttribute("productList", po);

            contentPage = "shop.jsp";
            pageTitle = "Shop";
        }else if ("product".equals(view)) {
        	String productId=request.getParameter("product_id");
        	int id=Integer.parseInt(productId);
        	productViewModel po=pm.getProductDetail(id);
        	ArrayList<productViewModel> p=pm.GetRelatedProduct(id);
        	
        	request.setAttribute("relatedProduct", p);
        	request.setAttribute("product", po);
        	
        	int currentPage = getPageParam(request);
            int itemsPerPage = 9;
            int totalItems = prm.getTotalProductReview(id);
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;
            
        	ArrayList<ProductReviewViewModel> prv=prm.getProductReview(id, offset, (byte) itemsPerPage);
        	
        	List<Integer> paginationData = generatePagination(currentPage, total_pages);
             
        	request.setAttribute("review", prv);
        	request.setAttribute("paginationData", paginationData);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
            request.setAttribute("totalItems", totalItems);
        	
            contentPage = "product.jsp";
            pageTitle = "Thông tin sản phẩm";
        }else if ("category".equals(view)) {
        	String cat_id=request.getParameter("cat_id");
        	
        	if (cat_id==null) {
        		response.sendRedirect(request.getContextPath() + "/page?view=home");
        		return;
			}
        	ProductCategory cat=cm.getProductCategory(Integer.parseInt(cat_id));
        	request.setAttribute("catName", cat.getCategory_name());
        	List<Integer> catIds=new ArrayList<Integer>();
        	
        	ArrayList<ProductCategory> c=cm.getAllSubCategories(Integer.parseInt(cat_id));
        	if(c!=null) {
        		for (ProductCategory productCategory : c) {
					catIds.add(productCategory.getCategory_id());
				}
        		catIds.add(Integer.parseInt(cat_id));
        	}else {
				catIds.add(Integer.parseInt(cat_id));
			}
        	
        	int currentPage = getPageParam(request);
            int itemsPerPage = 9;
            int totalItems = pm.GetTotalProductByCategory(catIds);
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;
            
            ArrayList<productViewModel> list=pm.getProductByCategory(catIds,offset, (byte) itemsPerPage);
        	
            List<Integer> paginationData = generatePagination(currentPage, total_pages);
            
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
            request.setAttribute("catId", cat_id);
        	request.setAttribute("products", list);
        	
            contentPage = "category.jsp";
            pageTitle = "Danh mục sản phẩm";
        }else if ("search".equals(view)) {
        	String words=request.getParameter("query");
        	
        	int currentPage = getPageParam(request);
            int itemsPerPage = 9;
            int totalItems = pm.GetTotalProductByWord(words);
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;
            
            ArrayList<productViewModel> list=pm.getProductByWord(words,offset, (byte) itemsPerPage);
        	
            List<Integer> paginationData = generatePagination(currentPage, total_pages);
            
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("word", words);
            request.setAttribute("totalItems", totalItems);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
    		request.setAttribute("result", list);
            contentPage = "search.jsp";
            pageTitle = "Kết quả tìm kiếm - "+words;
        }else if ("brand".equals(view)) {
        	String[] selectedBrands=request.getParameterValues("brands");
        	List<String> brands=new ArrayList<String>();
        	if (selectedBrands != null) {
        	    for (String br : selectedBrands) {
        	        brands.add(br);
        	    }
        	} else {
        	    System.out.println("Không có thương hiệu nào được chọn");
        	    response.sendRedirect(request.getContextPath() + "/page?view=shop");
        	    return;
        	}
        	
        	int currentPage = getPageParam(request);
            int itemsPerPage = 9;
            int totalItems = pm.GetTotalProductByBrand(brands);
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;
            
            ArrayList<productViewModel> list=pm.GetProductByBrand(brands,offset, (byte) itemsPerPage);
        	
            List<Integer> paginationData = generatePagination(currentPage, total_pages);
            
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("totalItems", totalItems);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
    		request.setAttribute("productList", list);
            contentPage = "brand.jsp";
            pageTitle = "Tìm kiếm theo thương hiệu";
        }else if ("cart".equals(view)) {
            if (user != null) {
                CartObject co = cam.getCart(userId);
                if (co == null) {
                    CartObject c = new CartObject();
                    c.setUser_id(userId);
                    boolean addResult = cam.addCart(c);
                    if (!addResult) {
                        request.setAttribute("cartitems", null);
                        return;
                    }
                    co = c; // set lại co để tránh null
                }

                ArrayList<cartItemViewModel> list = cim.getCartItems(userId);
                request.setAttribute("cartitems", list);
                request.setAttribute("cart_id", co.getCart_id());

                // Cho phép truy cập checkout
                session.setAttribute("allowCheckout", true);
            } else {
                request.setAttribute("cartitems", null);
                session.setAttribute("allowCheckout", false);
            }
            contentPage = "cart.jsp";
            pageTitle = "Giỏ hàng";

        } else if ("checkout".equals(view)) {
            if (user != null) {
                Boolean allowCheckout = (Boolean) session.getAttribute("allowCheckout");
                if (allowCheckout == null || !allowCheckout) {
                    response.sendRedirect(request.getContextPath() + "/page?view=cart");
                    return;
                }

                ArrayList<cartItemViewModel> list = cim.getCartItems(userId);
                session.setAttribute("cart", list);

                session.setAttribute("allowCheckout", false);

            } else {
                session.setAttribute("notification_message", "Bạn phải đăng nhập mới có thông tin");
                session.setAttribute("notification_type", "warning");
                response.sendRedirect(request.getContextPath() + "/page?view=login");
                return;
            }
            contentPage = "checkout.jsp";
            pageTitle = "Thanh toán";
        }else if ("profile".equals(view)) {
        	if (user!=null) {
        		ArrayList<OrderObject> list=om.getOrders(userId);
            	request.setAttribute("orders", list);
            	String fulladress=user.getUser_address();
            	if (fulladress!=null) {
                	String[] addressParts = fulladress.split(",");

                	String address = "";
                	String ward = "";
                	String district = "";
                	String city = "";

                	if (addressParts.length >= 4) {
                		address = String.join(", ", Arrays.copyOfRange(addressParts, 0, addressParts.length - 3)); 
                	    ward = addressParts[addressParts.length - 3].trim();
                	    district = addressParts[addressParts.length - 2].trim();
                	    city = addressParts[addressParts.length - 1].trim();
                	} else {
                	    address = fulladress;
                	}

                	request.setAttribute("address", address);
                	request.setAttribute("ward", ward);
                	request.setAttribute("district", district);
                	request.setAttribute("city", city);
    			}else {
    				request.setAttribute("address", "");
                	request.setAttribute("ward", "");
                	request.setAttribute("district", "");
                	request.setAttribute("city", "");
    			}
            	
            	request.setAttribute("user", user);
            	
            	String fullname=user.getUser_fullname();
            	if (fullname!=null) {
            		String[] parts = fullname.trim().split("\\s+");
                    String lastName = parts[parts.length - 1];
                    request.setAttribute("FisrtCharName", lastName.charAt(0));
    			} else {
    				request.setAttribute("FisrtCharName", "");
    			}
                
			} else {
				session.setAttribute("notification_message", "Bạn phải đăng nhập mới có thông tin");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=login");
	            return;
			}
        	contentPage = "profile.jsp";
            pageTitle = "Thông tin tài khoản";
        }else if ("order".equals(view)) {
        	if (user!=null) {
        		String id=request.getParameter("order_id");
            	int orderId=Integer.parseInt(id);
            	orderView ov=om.getOrder(orderId);
            	request.setAttribute("order", ov);
            	
            	ArrayList<orderDetailView> odv=odm.getOrderDetails(orderId);
            	request.setAttribute("orderDetails", odv);
			} else {
				session.setAttribute("notification_message", "Bạn phải đăng nhập mới có thông tin");
	            session.setAttribute("notification_type", "warning");
	            response.sendRedirect(request.getContextPath() + "/page?view=login");
	            return;
			}
            contentPage = "order.jsp";
            pageTitle = "Chi tiết đơn hàng";
        }else if ("news".equals(view)) {
        	int currentPage = getPageParam(request);
            int itemsPerPage = 6;
            int totalItems = nm.getTotalNews();
            int total_pages = (int) Math.ceil((double) totalItems / itemsPerPage);
            int offset = (currentPage - 1) * itemsPerPage;
            
        	ArrayList<NewsObject> listNews=nm.getNews(null, offset, (byte) itemsPerPage);
        	List<Integer> paginationData = generatePagination(currentPage, total_pages);
        	
        	ArrayList<NewsObject> hotNews=nm.getHotNews();
            request.setAttribute("hotNews", hotNews);
        	
            request.setAttribute("paginationData", paginationData);
            request.setAttribute("totalItems", totalItems);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", total_pages);
        	request.setAttribute("news", listNews);
        	
            contentPage = "news.jsp";
            pageTitle = "Tin tức";
        }else if ("news_detail".equals(view)) {
        	String id=request.getParameter("news_id");
        	
        	if (id==null || id.trim().isEmpty() || id.equals("0")) {
        		response.sendRedirect(request.getContextPath() + "/page?view=news");
	            return;
			}
        	int newsId=Integer.parseInt(id);
        	NewsObject no=nm.getNews(newsId);
        	request.setAttribute("news", no);
        	
        	ArrayList<NewsObject> hotNews=nm.getHotNews();
            request.setAttribute("hotNews", hotNews);
            
            ArrayList<Integer> adjacentId=nm.getAdjacentNews(newsId);
            Integer prevId = adjacentId.get(0);
            Integer nextId = adjacentId.get(1);
            
            request.setAttribute("pre_id", prevId);
            request.setAttribute("next_id", nextId);
            
            contentPage = "news_detail.jsp";
            pageTitle = "Tin tức";
        }else {
            contentPage = "404.jsp";
            pageTitle = "Không tìm thấy";
        }

        request.setAttribute("contentPage", contentPage);
        request.setAttribute("pageTitle", pageTitle);
        request.getRequestDispatcher("layout.jsp").forward(request, response);
	}
	
	private int getPageParam(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }
	
	public static List<Integer> generatePagination(int currentPage, int totalPages) {
	    List<Integer> pages = new ArrayList<>();

	    if (totalPages <= 8) {
	        for (int i = 1; i <= totalPages; i++) {
	            pages.add(i);
	        }
	    } else {
	        pages.add(1);

	        if (currentPage <= 4) {
	            for (int i = 2; i <= 5; i++) {
	                pages.add(i);
	            }
	            pages.add(0);
	        } else if (currentPage >= totalPages - 3) {
	            pages.add(0);
	            for (int i = totalPages - 4; i < totalPages; i++) {
	                pages.add(i);
	            }
	        } else {
	            pages.add(0);
	            pages.add(currentPage - 1);
	            pages.add(currentPage);
	            pages.add(currentPage + 1);
	            pages.add(0);
	        }

	        pages.add(totalPages);
	    }

	    return pages;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
