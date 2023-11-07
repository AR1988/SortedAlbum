package dao;


import model.Photo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AlbumImpl implements Album {
    private Photo[] photos;
    private int size;

    // c = 5
    // size = 0
    // [null,null,null,null,null]

    // add -> 1
    // [null,null,null,null,null]
    // [1,null,null,null,null]
    // size = 1

    // add -> 55
    // [1,null,null,null,null]
    // [1,55,null,null,null]
    // size = 2

    public AlbumImpl(int capacity) {
        this.photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        boolean isInvalid = photo == null;
        if (isInvalid) {
            return false;
        }

        if (size == photos.length) {
            return false;
        }

        int insertIdx = Arrays.binarySearch(photos, 0, size, photo);
        if (insertIdx >= 0) {
            return false; // элемент уже существует
        }

        insertIdx = -insertIdx - 1; // Если элемент не найден binarySearch возвращает отрицательный индекс для вставки

        // 1,2,6,7
        // 3 (num to add)
        // insertIdx = 2 (line 44)
        // 1,2,null,6,7 (раздвинуть массив, строка 52)
        // 1,2,3,6,7

        System.arraycopy(photos, insertIdx, photos, insertIdx + 1, size - insertIdx);
        photos[insertIdx] = photo;
        size++;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        // Создать временный объект для поиска
        Photo template = new Photo(albumId, photoId);

        // Выполнить поиск
        int intexToRemove = Arrays.binarySearch(photos, 0, size, template);

        // если индекс >= 0, то удалить элемент, сместить массив.
        if (intexToRemove >= 0) {
            System.arraycopy(photos, intexToRemove + 1, photos, intexToRemove, size - intexToRemove - 1);
            photos[--size] = null;
            return true;
        }


        return false;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        Photo photoFromAlbum = getPhotoFromAlbum(photoId, albumId);

        if (photoFromAlbum != null) {
            photoFromAlbum.setUrl(url);
            return true;
        }

        return false;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        // Создать временный объект для поиска
        Photo template = new Photo(albumId, photoId);
        // Выполнить поиск
        int index = Arrays.binarySearch(photos, 0, size, template);
        // если индекс >= 0, то вернуть элемент
        if (index >= 0) {
            return photos[index];
        }

        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        Photo[] result = new Photo[size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId) {
                result[counter++] = photos[i];
            }
        }

        return Arrays.copyOf(result, counter);
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {

        LocalDateTime dateFromPlusTime = dateFrom.atStartOfDay();
        LocalDateTime dateToPlusTime = dateTo.plusDays(1).atStartOfDay();

        Photo[] arrayPhotoBetweenDate = new Photo[size];

        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (photos[i].getDate().isAfter(dateFromPlusTime) && photos[i].getDate().isBefore(dateToPlusTime)) {
                arrayPhotoBetweenDate[counter++] = photos[i];
            }
        }

        return Arrays.copyOf(arrayPhotoBetweenDate, counter);
    }

    @Override
    public int size() {
        return size;
    }
}
