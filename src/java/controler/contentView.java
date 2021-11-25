package controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.contentModel;
import dao.contentDAO;

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
		RequestDispatcher dispatcher;
		contentDAO content = new contentDAO();
		int numPage = content.getNumPage();
		int numId = content.getNumId();
		if (numId == 0) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		} else {
			String NumPageButton = "";
			int currentPage = 1;
			while (currentPage <= 5) {
				if (currentPage <= numPage) {
					NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentPage + 5) + "="
							+ Integer.toString(currentPage);
					currentPage++;
				} else
					break;
				if (currentPage != numPage && currentPage == 5)
					NumPageButton = NumPageButton + "&BtLast=" + Integer.toString(numPage);
			}
			if (numPage >= 1) {
				List<contentModel> listContent = content.selectAllContents(10, 0);
				request.setAttribute("listContent", listContent);
				 dispatcher = request
						.getRequestDispatcher("view-content.tiles?Page=0&numId=10" + NumPageButton);
				dispatcher.forward(request, response);
			} else {
				List<contentModel> listContent = content.selectAllContents(numId, 0);
				request.setAttribute("listContent", listContent);
				 dispatcher = request
						.getRequestDispatcher("view-content.tiles?Page=0&numId= " + numId + NumPageButton);
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contentDAO content = new contentDAO();
		int numPage = content.getNumPage();
		int pageReq = Integer.parseInt(request.getParameter("Page"));
		if (numPage < pageReq || 0 > pageReq) {
			response.sendRedirect("view-content.tiles?Page=0&numId=0");
		} else {
			String NumPageButton = "";
			int currentBt = 1;
			int butP = 1;
			if (numPage != 0) {
				{
					while (currentBt <= 5) {
						if (pageReq - butP >= 0) {
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
									+ Integer.toString(pageReq - butP);
							currentBt++;
							butP++;
						} else
							break;
						if (pageReq - butP - 1 >0 && currentBt == 5) {
							NumPageButton = NumPageButton + "&BtFirst=" + Integer.toString(0);
						}
					}
					currentBt=6;
					butP = 1;
					while (currentBt <= 10) {
						if (pageReq + butP <= numPage) {
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
									+ Integer.toString(pageReq + butP);
							currentBt++;
							butP++;
						} else
							break;
						if (pageReq + butP - 1 < numPage && currentBt == 10) {
							NumPageButton = NumPageButton + "&BtLast=" + Integer.toString(numPage);
						}
					}
				}
			}
			int numId = content.getNumId();
			if (pageReq != 0) {
				numId = numId - numPage * 10;
				List<contentModel> listContent = content.selectAllContents(10, pageReq);
				request.setAttribute("listContent", listContent);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("view-content.tiles?Page=" + pageReq + "&numId=" + numId + NumPageButton);
				dispatcher.forward(request, response);
			} else {

				if (numPage >= 1) {
					List<contentModel> listContent = content.selectAllContents(10, pageReq);
					request.setAttribute("listContent", listContent);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("view-content.tiles?Page=0&numId=10" + NumPageButton);
					dispatcher.forward(request, response);
				} else {
					List<contentModel> listContent = content.selectAllContents(numId, pageReq);
					request.setAttribute("listContent", listContent);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("view-content.tiles?Page=0&numId=" + numId + NumPageButton);
					dispatcher.forward(request, response);
				}
			}
		}
	}

}
