
package cn.scau.hjr.filter;

import cn.scau.hjr.model.SystemData;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Administrator
 */
public class PagerFilter implements Filter {

    private FilterConfig filterConfig = null;

    private Integer pagesize = 0;

    public PagerFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            Integer pageOffset = 0;

            try {
                pageOffset = Integer.parseInt(request.getParameter("pageindex"));
            } catch (NumberFormatException nex) {
                pageOffset=1;
            }

            SystemData.setPageOffset(pageOffset);
            SystemData.setPageSize(this.pagesize);
            chain.doFilter(request, response);
        } finally {
            SystemData.removePageOffset();
            SystemData.removePageSize();
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {

        this.filterConfig = null;
        this.pagesize = 0;
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        int pageSize = 15;

        try {
            Integer _pagesize = Integer.parseInt(filterConfig.getInitParameter("pagesize"));
            this.pagesize = _pagesize;
        } catch (NumberFormatException nex) {
            this.pagesize = pageSize;

        }

    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SystemFilter()");
        }
        StringBuffer sb = new StringBuffer("SystemFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

}
