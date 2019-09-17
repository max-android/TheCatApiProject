package com.my_project.thecatapiproject.diffutil_callback_sample.data

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
class TypeMapper {

    companion object {
        fun mapApiToUI(apiType: TypeApi): MyType {
            return when {
                apiType.title != null && apiType.description != null -> MyType1(
                    apiType.id,
                    apiType.title,
                    apiType.description
                )
                apiType.url2 != null && apiType.info2 != null -> MyType2(
                    apiType.id,
                    apiType.url2,
                    apiType.info2
                )
                apiType.url3 != null && apiType.info3 != null -> MyType3(
                    apiType.id,
                    apiType.url3,
                    apiType.info3
                )
                else -> throw Exception("no exist entities")
            }

        }
    }

}