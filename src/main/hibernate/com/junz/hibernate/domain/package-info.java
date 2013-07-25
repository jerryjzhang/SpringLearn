@TypeDef
(  
  name = "HibernateTimestampAdapter",
  defaultForType = java.sql.Timestamp.class, /* applied globally */
  typeClass = com.junz.hibernate.time.CustomTimestampType.class
)

package com.junz.hibernate.domain;

import org.hibernate.annotations.TypeDef;