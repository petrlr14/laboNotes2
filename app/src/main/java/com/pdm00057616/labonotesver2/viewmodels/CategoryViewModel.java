package com.pdm00057616.labonotesver2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pdm00057616.labonotesver2.database.repositories.CategoryRepository;
import com.pdm00057616.labonotesver2.models.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository repository;
    private LiveData<List<Category>> allCategory;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new CategoryRepository(application);
        allCategory = repository.getAllCategory();
    }

    public LiveData<List<Category>> getAllCategory() {
        return allCategory;
    }

    public void insert(Category category) {
        repository.insertCategory(category);
    }
}
