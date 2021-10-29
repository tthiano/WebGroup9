package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContentBean;

public class contentView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contentView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContentBean ContentBean=new ContentBean();
		int numPage=ContentBean.getNumPage();
		int numId=ContentBean.getNumId();
		if(numId==0) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		}
		else {
			numId= numId-numPage*10;
			response.sendRedirect("view-content.tiles?Page=0&numId="+numId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContentBean ContentBean=new ContentBean();
		int numPage=ContentBean.getNumPage();
		int pageReq=Integer.parseInt(request.getParameter("Page"));
		if(numPage<pageReq||0>pageReq) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		}
		else {
			int numId=ContentBean.getNumId();
			numId= numId-numPage*10;
			response.sendRedirect("view-content.tiles?Page="+pageReq+"&numId="+numId);
		}
	}

}
