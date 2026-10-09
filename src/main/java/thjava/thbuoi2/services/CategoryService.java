package thjava.thbuoi2.services;

import thjava.thbuoi2.models.Category;
import thjava.thbuoi2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 1. Lấy toàn bộ danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 2. Tìm danh mục theo ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // 3. Thêm một danh mục
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // 4. Cập nhật danh mục
    public void updateCategory(@NotNull Category category) {
        Category existingCategory = categoryRepository.findById(category.getId())
            .orElseThrow(() -> new IllegalStateException("Danh mục ID " + category.getId() + " không tồn tại."));
        existingCategory.setName(category.getName());
        categoryRepository.save(existingCategory);
    }

    // 5. Xóa danh mục theo ID
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalStateException("Danh mục ID " + id + " không tồn tại.");
        }
        categoryRepository.deleteById(id);
    }
}