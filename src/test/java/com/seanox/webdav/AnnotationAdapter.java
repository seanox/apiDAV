/**
 * LIZENZBEDINGUNGEN - Seanox Software Solutions ist ein Open-Source-Projekt,
 * im Folgenden Seanox Software Solutions oder kurz Seanox genannt.
 * Diese Software unterliegt der Version 2 der GNU General Public License.
 *
 * WebDAV mapping for Spring Boot
 * Copyright (C) 2021 Seanox Software Solutions
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of version 2 of the GNU General Public License as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package com.seanox.webdav;

import java.lang.reflect.Method;

/**
 * Testing private parts and/or components visible only in the package requires
 * an adapter for access.<br>
 * <br>
 * Why are the tests not in com.seanox.webdav?<br>
 * Spring Test is used for the tests. For this @ComponentScan must scan the
 * package. For the release version, however, it should be ensured that the
 * library com.seanox.webdav also works without @ComponentScan and therefore
 * another package is used for the tests of the package com.seanox.webdav.<br>
 * <br>
 * AnnotationAdapter 1.0.0 20210725<br>
 * Copyright (C) 2021 Seanox Software Solutions<br>
 * All rights reserved.
 *
 * @author  Seanox Software Solutions
 * @version 1.0.0 20210725
 */
public class AnnotationAdapter {

    public static Annotation.Attribute createAnnotationAttribute(final WebDavAttributeMapping attributeAnnotation,
            final Object object, final Method method) {
        return Annotation.Attribute.create(attributeAnnotation, object, method);
    }

    public static Annotation.Input createAnnotationInput(final WebDavInputMapping inputAnnotation,
            final Object object, final Method method) {
        return Annotation.Input.create(inputAnnotation, object, method);
    }

    public static Annotation.Mapping createAnnotationMapping(final WebDavMapping mappingAnnotation,
            final Object object, final Method method) throws AnnotationException {
        return Annotation.Mapping.create(mappingAnnotation, object, method);
    }

    public static Annotation createAnnotation(final java.lang.annotation.Annotation annotation)
            throws AnnotationException {
        return AnnotationAdapter.createAnnotation(annotation, null, null);
    }

    public static Annotation createAnnotation(final java.lang.annotation.Annotation annotation, final Object object, final Method method)
            throws AnnotationException {
        if (annotation.annotationType().equals(WebDavAttributeMapping.class))
            return AnnotationAdapter.createAnnotationAttribute((WebDavAttributeMapping)annotation, object, method);
        else if (annotation.annotationType().equals(WebDavInputMapping.class))
            return AnnotationAdapter.createAnnotationInput((WebDavInputMapping)annotation, object, method);
        else if (annotation.annotationType().equals(WebDavMapping.class))
            return AnnotationAdapter.createAnnotationMapping((WebDavMapping)annotation, object, method);
        throw new AdapterException("Unsupported annotation type: " + annotation.annotationType());
    }
}