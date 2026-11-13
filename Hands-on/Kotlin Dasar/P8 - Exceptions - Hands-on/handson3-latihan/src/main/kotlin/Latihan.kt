// Hands-on 3: Kotlin Sugar - try sebagai expression, require(), error(), multi-catch
// Tugas: Buat parser tiket sederhana dari format teks "nama/umur",
// misalnya "Budi/25", menggunakan gaya penanganan error khas Kotlin.
//
// CATATAN: file ini SENGAJA belum bisa di-compile sebelum semua TODO
// dilengkapi - itu bagian dari latihan ini.

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    val umur: Int = try {
        bagian[1].toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: ${bagian[1]}")
    }

    require(nama.isNotBlank()) { "Nama tidak boleh kosong" }
    require(umur >= 0) { "Umur harus >= 0" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf("Budi/25", "Siti/17", "format-salah", "Andi/bukan-angka", "/30")

    for (input in inputs) {
        try {
            println(parseTiket(input))
        } catch (e: IllegalArgumentException) {
            println("Kesalahan argumen untuk '$input': ${e.message}")
        } catch (e: IllegalStateException) {
            println("Kesalahan state untuk '$input': ${e.message}")
        }
    }
}
