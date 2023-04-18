package practice.tubbydatasource

import java.io.File

class TubbyDataSource(
    val storageFile: File
) {
    init {
        reloadFromFile()
    }

    private lateinit var storageMap: HashMap<Class<*>, Table<*>>

    private fun initializeClassInStorageMapIfNeeded(clazz: Class<*>) {

        if (storageMap[clazz] == null) {
            storageMap[clazz] = Table(AutoIncrementer(currentValue = 1), ArrayList())
        }
    }

    fun <T : Entity> save(entityToSave: T) {
        initializeClassInStorageMapIfNeeded(entityToSave.javaClass)

        val table = storageMap[entityToSave.javaClass]!! as Table<T>

        if (entityToSave.id == null) {
            entityToSave.id = table.autoIncrementer.getNextValue()
        } else {
            table.records.removeIf { it.id == entityToSave.id }
        }

        table.records.add(entityToSave)
        persistToFile()
    }

    fun <T : Entity> getById(clazz: Class<T>, idToGet: Int): T? {
        initializeClassInStorageMapIfNeeded(clazz)

        return (storageMap[clazz]!! as Table<T>).records.firstOrNull { it.id == idToGet }
    }

    fun <T : Entity> getAll(clazz: Class<T>): List<T> {
        initializeClassInStorageMapIfNeeded(clazz)

        return (storageMap[clazz]!! as Table<T>).records.map { it }
    }

    fun <T : Entity> deleteById(clazz: Class<T>, idToDelete: Int) {
        initializeClassInStorageMapIfNeeded(clazz)


        (storageMap[clazz]!! as Table<T>).records.removeIf { it.id == idToDelete }
    }

    fun persistToFile() {
        FileAccessHelper.persist(storageFile, storageMap)
    }

    fun reloadFromFile() {
        storageMap = FileAccessHelper.load(storageFile)
    }
}


//fun genericsDemo() {
//    val ds = TubbyDataSource()
//
//    val ma: MailingAddress = ds.getById(MailingAddress::class.java, 5)
//    val t: Task = ds.getById(Task::class.java, 5)
//}