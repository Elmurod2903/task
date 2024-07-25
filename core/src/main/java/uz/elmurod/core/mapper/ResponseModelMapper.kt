package uz.elmurod.core.mapper

interface ResponseModelMapper<Response, Model> {

    fun mapFromResponse(response: Response): Model

    open fun mapFromResponseList(list: List<Response>): List<Model> {
        return ArrayList<Model>().apply {
            list.forEach {
                add(mapFromResponse(it))
            }
        }
    }
}