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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContentBean ContentBean = new ContentBean();
		int numPage = ContentBean.getNumPage();
		int numId = ContentBean.getNumId();
		if (numId == 0) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		} else {
			String NumPageButton = "";
			int currentPage = 1;
			if (numPage != 0 && numId > 10) {
				if (numPage <= 5) {
					while (currentPage <= numPage) {
						NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentPage) + "="
								+ Integer.toString(currentPage);
						currentPage++;
					}
				} else {
					while (currentPage <= 4) {
						NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentPage) + "="
								+ Integer.toString(currentPage);
						currentPage++;
					}
					NumPageButton = NumPageButton + "&Bt=" + Integer.toString(numPage);
				}
			}
			if (numPage >= 1) {
				response.sendRedirect("view-content.tiles?Page=0&numId=10" + NumPageButton);
			} else {
				response.sendRedirect("view-content.tiles?Page=0&numId=" + numId + NumPageButton);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContentBean ContentBean = new ContentBean();
		int numPage = ContentBean.getNumPage();
		int pageReq = Integer.parseInt(request.getParameter("Page"));
		if (numPage < pageReq || 0 > pageReq) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		} else {
			String NumPageButton = "";
			int currentPage = 1;
			int pageAdded = 1;
			int beforeAfter = -1;
			if (numPage != 0) {
				{
					int break_check = 0;
					while (currentPage <= 4) {
						if (pageReq + beforeAfter * pageAdded > 0 && break_check == 0) {
							if (pageReq + beforeAfter * pageAdded < 0 || pageReq + beforeAfter * pageAdded > numPage) {
								break;
							}
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(pageReq + beforeAfter * pageAdded)
									+ "=" + Integer.toString(pageReq + beforeAfter * pageAdded);
							if (beforeAfter == -1) {
								beforeAfter = 1;
							} else {
								beforeAfter = -1;
								pageAdded++;
							}
							currentPage++;
						} else {
							if (pageReq != 0 && pageReq + beforeAfter * pageAdded < 0
									|| pageReq + beforeAfter * pageAdded > numPage) {
								break;
							}
							if (pageReq != 0) {
								if (pageReq + beforeAfter * pageAdded == 0)
									break;
							}
							if (break_check == 0) {
								beforeAfter *= -1;
								break_check = 1;
							}
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(pageReq + beforeAfter * pageAdded)
									+ "=" + Integer.toString(pageReq + beforeAfter * pageAdded);
							pageAdded++;
							currentPage++;
						}
					}
					if (pageReq != 0) {
						if (pageAdded + pageReq >= numPage) {
							NumPageButton = NumPageButton + "&Bt5=0";
						} else {
							NumPageButton = NumPageButton + "&Bt5=0";
							NumPageButton = NumPageButton + "&Bt6=" + Integer.toString(numPage);
						}
					}
				}
			}
			int numId = ContentBean.getNumId();
			if (pageReq != 0) {
				numId = numId - numPage * 10;
				response.sendRedirect("view-content.tiles?Page=" + pageReq + "&numId=" + numId + NumPageButton);
			} else {

				if (numPage >= 1) {
					response.sendRedirect("view-content.tiles?Page=0&numId=10" + NumPageButton);
				} else {
					response.sendRedirect("view-content.tiles?Page=0&numId=" + numId + NumPageButton);
				}
			}
		}
	}

}
