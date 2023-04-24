package practice.tubbydatasource

import java.io.File
import kotlin.math.max

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

    /**
     * Persists an entity into the database. If the entity's id is null, it generates and sets a new valid id for the entity.
     */
    fun <T : Entity> save(entityToSave: T) {
        initializeClassInStorageMapIfNeeded(entityToSave.javaClass)

        val table = storageMap[entityToSave.javaClass]!! as Table<T>

        if (entityToSave.id == null) {
            entityToSave.id = table.autoIncrementer.getNextValue()
        } else {
            table.records.removeIf { it.id == entityToSave.id }
            table.autoIncrementer.currentValue = max(table.autoIncrementer.currentValue, entityToSave.id!! + 1)
        }

        table.records.add(entityToSave)
        persistToFile()
    }

    /**
     * Gets a [clazz] type entity with the given [idToGet]. If no entity found with the given id, it return null.
     */
    fun <T : Entity> getById(clazz: Class<T>, idToGet: Int): T? {
        initializeClassInStorageMapIfNeeded(clazz)

        return (storageMap[clazz]!! as Table<T>).records.firstOrNull { it.id == idToGet }
    }

    /**
     * @return a list of all the entities with the given [clazz] type
     */
    fun <T : Entity> getAll(clazz: Class<T>): List<T> {
        initializeClassInStorageMapIfNeeded(clazz)

        return (storageMap[clazz]!! as Table<T>).records.map { it }
    }

    /**
     * Deletes a [clazz] type entity with the given [idToDelete]. If no entity found with the given id, this function does nothing.
     */
    fun <T : Entity> deleteById(clazz: Class<T>, idToDelete: Int) {
        initializeClassInStorageMapIfNeeded(clazz)


        (storageMap[clazz]!! as Table<T>).records.removeIf { it.id == idToDelete }
        persistToFile()
    }

    /**
     * Force-save the contents of the database into the file
     */
    fun persistToFile() {
        FileAccessHelper.persist(storageFile, storageMap)
    }

    /**
     * Force-reload the contents of the database from the file
     */
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