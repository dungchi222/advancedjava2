package com.samsung.basicsecurity.repositories;

import com.samsung.basicsecurity.repositories.models.Catalog;

import java.util.List;

public interface MenuBuilder {
    String buildMenu(List<Catalog> catalogs);
}
