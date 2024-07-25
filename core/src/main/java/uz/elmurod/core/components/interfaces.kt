package uz.elmurod.core.components

fun interface OnActionListener<Data> {
    fun onClick(data: Data)
}