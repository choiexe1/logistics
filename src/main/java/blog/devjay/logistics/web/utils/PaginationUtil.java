package blog.devjay.logistics.web.utils;

import blog.devjay.logistics.dto.AbstractPaginable;
import lombok.Data;

@Data
public class PaginationUtil {
    private static final int MAX_PAGE = 5;
    private static final int PAGE_OFFSET = 1;
    private static final int PAGE_RANGE = MAX_PAGE - 1;

    private final AbstractPaginable paginable;
    private final int totalPageCount;

    public int[] rowsPerPage() {
        return new int[]{10, 20, 40};
    }

    public PaginationUtil(AbstractPaginable paginable, int totalPageCount) {
        this.paginable = paginable;
        this.totalPageCount = totalPageCount;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalPageCount / paginable.getSize());
    }

    public int getStartPage() {
        return ((paginable.getPage() - PAGE_OFFSET) / MAX_PAGE) * MAX_PAGE + PAGE_OFFSET;
    }

    public int getEndPage() {
        return Math.min(getStartPage() + PAGE_RANGE, getTotalPages());
    }
}
