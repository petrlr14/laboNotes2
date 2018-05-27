package com.pdm00057616.labonotesver2.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdm00057616.labonotesver2.database.DB;
import com.pdm00057616.labonotesver2.database.daos.CategoryDao;
import com.pdm00057616.labonotesver2.models.Category;

import java.util.List;

public class CategoryRepository {

    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> allCategory;

    public CategoryRepository(Application application) {
        DB db = DB.getInstance(application);
        mCategoryDao = db.categoryDao();
        allCategory = mCategoryDao.getAllCategory();
    }

    public LiveData<List<Category>> getAllCategory() {
        return allCategory;
    }

    public void insertCategory(Category category) {
        new insertCategoryAsyncTask(mCategoryDao).execute(category);
    }

    private static class insertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao mCategoryDao;

        private insertCategoryAsyncTask(CategoryDao mCategoryDao) {
            this.mCategoryDao = mCategoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            mCategoryDao.insert(categories[0]);
            return null;
        }
    }
}
