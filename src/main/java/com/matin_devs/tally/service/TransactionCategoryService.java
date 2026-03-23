package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.CategoryRequest;
import com.matin_devs.tally.model.TransactionCategory;
import com.matin_devs.tally.repository.TransactionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;
    public TransactionCategory createCategory(CategoryRequest request) {

        TransactionCategory category = TransactionCategory.builder()
                .name(request.getName())
                .user(request.getUser())
                .build();

        transactionCategoryRepository.save(category);
        return category;
    }

    //TODO: ADD DELETING CATEGORY FUNCTIONALITY
}
