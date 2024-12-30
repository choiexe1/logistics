package blog.devjay.logistics.dto;

import lombok.Data;
import org.springframework.ui.Model;

@Data
public abstract class AbstractPaginable implements SearchDTO {
    private static final int MAX_PAGE = 5;
    private static final int PAGE_OFFSET = 1;
    private static final int PAGE_RANGE = MAX_PAGE - 1;
    private int size = 10;
    private int page = 1;
    private int totalPageCount;

    public void setPagination(Model model, int totalPageCount) {
        this.totalPageCount = totalPageCount;

        model.addAttribute("rowsPerPage", rowsPerPage());
        model.addAttribute("totalPages", getTotalPages());
        model.addAttribute("startPage", getStartPage());
        model.addAttribute("endPage", getEndPage());
    }

    private int[] rowsPerPage() {
        return new int[]{10, 20, 40};
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) totalPageCount / size);
    }

    private int getStartPage() {
        return ((page - PAGE_OFFSET) / MAX_PAGE) * MAX_PAGE + PAGE_OFFSET;
    }

    private int getEndPage() {
        return Math.min(getStartPage() + PAGE_RANGE, getTotalPages());
    }
}
