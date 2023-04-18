package practice.tubbydatasource

class Table<T : Entity>(
    val autoIncrementer: AutoIncrementer,
    val records: ArrayList<T>
) {
}