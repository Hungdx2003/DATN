package ads.pagecontrol;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.discount.discountModel;
import ads.image.imageModel;
import ads.news.newsModel;
import ads.objects.ProductCategory;
import ads.objects.ProductImage;
import ads.objects.ProductObject;
import ads.objects.RoleObject;
import ads.objects.UserObject;
import ads.order.orderModel;
import ads.payment.paymentModel;
import ads.permission.permissionModel;
import ads.objects.DiscountObject;
import ads.objects.ImageObject;
import ads.objects.NewsObject;
import ads.objects.OrderObject;
import ads.objects.PermissionObject;
import ads.product.productModel;
import ads.productCategory.categoryModel;
import ads.productImage.productImageModel;
import ads.role.roleModel;
import ads.user.userModel;
import ads.viewModel.productViewModel;


/**
 * Servlet implementation class PageControl
 */
@WebServlet("/page")
public class PageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private categoryModel cm;
    private productModel pm;
    private orderModel om;
    private userModel um;
    private roleModel rm;
    private discountModel dm;
    private productImageModel pim;
    private paymentModel paym;
    private newsModel nwm;
    private permissionModel psm;
    private imageModel im;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cm = new categoryModel();
        pm = new productModel();
        om=new orderModel();
        um=new userModel();
        rm=new roleModel();
        dm=new discountModel();
        pim=new productImageModel();
        paym=new paymentModel();
        nwm=new newsModel();
        psm=new permissionModel();
        im=new imageModel();
        
        dm.updateDiscountStatus();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageControl() {
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

        if ("home".equals(view)) {
        	ArrayList<Integer> count_revenue=paym.getRevenueData();
        	request.setAttribute("dailyRevenue", count_revenue.get(0));
        	request.setAttribute("monthlyRevenue", count_revenue.get(1));
        	request.setAttribute("yearlyRevenue", count_revenue.get(2));
        	
        	ArrayList<Integer> order_count=om.countOrder();
        	request.setAttribute("dailyOrder", order_count.get(0));
        	request.setAttribute("monthlyOrder", order_count.get(1));
        	request.setAttribute("yearlyOrder", order_count.get(2));
        	
        	ArrayList<Integer> quantity_count=om.countQuantity();
        	request.setAttribute("dailySold", quantity_count.get(0));
        	request.setAttribute("monthlySold", quantity_count.get(1));
        	request.setAttribute("yearlySold", quantity_count.get(2));
        	
        	ArrayList<productViewModel> count_day=pm.getBestSaleByDay();
        	ArrayList<productViewModel> count_month=pm.getBestSaleByMonth();
        	ArrayList<productViewModel> count_year=pm.getBestSaleByYear();
        	request.setAttribute("dailyBestSold", count_day);
        	request.setAttribute("monthlyBestSold", count_month);
        	request.setAttribute("yearlyBestSold", count_year);
        	
        	ArrayList<productViewModel> low_quan=pm.getLowQuantity();
        	request.setAttribute("lowQuantity", low_quan);
        	
        	ArrayList<productViewModel> slowSell=pm.getSlowSellingProduct();
        	request.setAttribute("slowSell", slowSell);
        	
            contentPage = "index.jsp";
            pageTitle = "Tổng quan";
        } else if ("layout".equals(view)) {
            contentPage = "layout.jsp";
            pageTitle = "HiStore";
        }else if ("role".equals(view)) {
        	ArrayList<RoleObject> roleList = rm.getRoleObjects();
            request.setAttribute("roleList", roleList);
            
            ArrayList<PermissionObject> list=psm.getObjectName();
            request.setAttribute("objectNames", list);
            
            contentPage = "role.jsp";
            pageTitle = "Phân quyền";
        }else if ("user".equals(view)) {
            ArrayList<UserObject> users = um.getUserObjects();
            request.setAttribute("users", users);
            ArrayList<RoleObject> roleList = rm.getRoleObjects();
            request.setAttribute("roleList", roleList);
            
            contentPage = "user.jsp";
            pageTitle = "Người dùng";
        } else if ("category".equals(view)) {
        	ArrayList<ProductCategory> pc = cm.getProductCategory(null, 0, (byte) 50);
        	request.setAttribute("category", pc);
           
        	contentPage = "category.jsp";
            pageTitle = "Danh mục sản phẩm";
        } else if ("product".equals(view)) {
        	ArrayList<ProductCategory> pc = cm.getProductCategory(null, 0, (byte) 50);
        	ArrayList<ProductObject> po=pm.getProduct(null, 0, (byte) 300);
        	for (ProductObject p : po) {
				ProductImage pi=pim.getProductImage(p.getProduct_id());
				p.setProduct_image(pi);
			}
        	
        	request.setAttribute("category", pc);
        	request.setAttribute("product", po);
        	
        	ArrayList<String> brand=pm.getBrand();
        	request.setAttribute("brand", brand);
        	
            contentPage = "product.jsp";
            pageTitle = "Danh sách sản phẩm";
        } else if ("discount".equals(view)) {
        	ArrayList<DiscountObject> di = dm.getDiscountObject(null, 0, (byte) 100);
        	request.setAttribute("discount", di);
        	
        	ArrayList<productViewModel> list_product=pm.getProductDiscount();
        	request.setAttribute("productNoDiscount", list_product);
        	
            contentPage = "discount.jsp";
            pageTitle = "Giảm giá";
        }else if ("news".equals(view)) {
        	ArrayList<NewsObject> no = nwm.getNews(); 
        	request.setAttribute("news", no);
        	
            contentPage = "news.jsp";
            pageTitle = "Tin tức";
        }else if ("order".equals(view)) {
        	ArrayList<OrderObject> order = om.getOrder(null, 0, (byte) 300);
        	request.setAttribute("order", order);
            
        	contentPage = "order.jsp";
            pageTitle = "Đơn hàng";
        }else if ("interface".equals(view)) {
        	ArrayList<ImageObject> list_banner=im.getImages("banner");
        	request.setAttribute("banner", list_banner);
        	
        	ArrayList<ImageObject> list_promo=im.getImages("promotion");
        	request.setAttribute("promo", list_promo);
        	
        	contentPage = "interface.jsp";
            pageTitle = "Giao diện";
        }else {
            contentPage = "404.jsp";
            pageTitle = "Không tìm thấy";
        }

        request.setAttribute("contentPage", contentPage);
        request.setAttribute("pageTitle", pageTitle);
        request.getRequestDispatcher("layout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
