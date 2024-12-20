package blog.devjay.logistics.web.utils;

public class PaginationUtil {
    private static final int MAX_PAGE = 5;
    private static final int PAGE_OFFSET = 1;
    private static final int PAGE_RANGE = MAX_PAGE - 1;

    public static int getTotalPages(int totalPageCount, int size) {
        return (int) Math.ceil((double) totalPageCount / size);
    }

    public static int getStartPage(int page) {
        return ((page - PAGE_OFFSET) / MAX_PAGE) * MAX_PAGE + PAGE_OFFSET;
    }

    public static int getEndPage(int startPage, int totalPages) {
        return Math.min(startPage + PAGE_RANGE, totalPages);
    }
}
