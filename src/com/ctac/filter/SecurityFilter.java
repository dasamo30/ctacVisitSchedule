package com.ctac.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	private static final boolean debug = true;
	private FilterConfig filterConfig = null;
	private long currTime = 0L;
	private long expiryTime = 0L;
	private final String urllogin = "/ctatVisitSchedule/login";

	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		this.log("SecurityFilter:DoBeforeProcessing");
	}

	private void doAfterProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		this.log("SecurityFilter:DoAfterProcessing");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		HttpSession session = req.getSession();
		this.currTime = System.currentTimeMillis() / 1000L;
		String uri = ((HttpServletRequest) request).getRequestURI();
		if (uri.matches(".*(css|jpg|png|gif|js|svg|eot|ttf|woff|woff2)")) {
			chain.doFilter(request, response);
		} else {
			if (!servletPath.equals("/login") && !servletPath.contains("/visitorLog")  && !servletPath.equals("/validatelogin")) {
				if (this.expiryTime == 0L) {
					if (session.getAttribute("usuario") == null) {
						session.setAttribute("err", "Usted no ha iniciado sesion");
						resp.sendRedirect("urllogin");
						this.expiryTime = -2L;
						return;
					}

					this.expiryTime = this.currTime + (long) session.getMaxInactiveInterval();
				} else {
					if (this.expiryTime < this.currTime) {
						session.setAttribute("err", "La sesion a expirado.");
						resp.sendRedirect(urllogin);
						this.expiryTime = -1L;
						return;
					}

					if (session.getAttribute("usuario") == null) {
						session.setAttribute("err", "Usted no ha iniciado sesion");
						resp.sendRedirect(urllogin);
						this.expiryTime = -2L;
						return;
					}

					this.expiryTime = this.currTime + (long) session.getMaxInactiveInterval();
				}
			} else if (session.getAttribute("err") != null) {
				String[] temp = session.getAttribute("err").toString().split(";");
				if (temp[0].equals("error_logeo")) {
					session.setAttribute("err", temp[1]);
				} else if (this.expiryTime == 0L) {
					session.setAttribute("err", "");
				} else if (this.expiryTime == -1L) {
					this.expiryTime = 0L;
				} else if (this.expiryTime == -2L) {
					this.expiryTime = 0L;
				}
			}

			chain.doFilter(req, resp);
		}
	}

	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			this.log("SecurityFilter:Initializing filter");
		}

	}

	public String toString() {
		if (this.filterConfig == null) {
			return "SecurityFilter()";
		} else {
			StringBuffer sb = new StringBuffer("SecurityFilter(");
			sb.append(this.filterConfig);
			sb.append(")");
			return sb.toString();
		}
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);
		PrintStream ps;
		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>");
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception arg6) {
				;
			}
		} else {
			try {
				ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception arg5) {
				;
			}
		}

	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;

		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception arg3) {
			;
		}

		return stackTrace;
	}

	public void log(String msg) {
		this.filterConfig.getServletContext().log(msg);
	}
}