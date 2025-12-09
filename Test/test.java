import java.util.Scanner;

public class MusicPlayerApp {

    // ====== STORAGE DATA MUSIC (paralel arrays) ======
    static final int MAX_MUSIC = 200;
    static String[] judul = new String[MAX_MUSIC];
    static String[] artis = new String[MAX_MUSIC];
    static String[] durasi = new String[MAX_MUSIC];
    static String[] album = new String[MAX_MUSIC];
    static int[] playCount = new int[MAX_MUSIC];
    static int jumlahMusic = 0;

    // ====== STORAGE PLAYLIST ======
    static final int MAX_PLAYLIST = 50;
    static final int MAX_MUSIC_PER_PLAYLIST = 200;
    static String[] namaPlaylist = new String[MAX_PLAYLIST];
    // isiPlaylist[p][i] menyimpan index lagu di array music (0..jumlahMusic-1). -1 berarti kosong
    static int[][] isiPlaylist = new int[MAX_PLAYLIST][MAX_MUSIC_PER_PLAYLIST];
    static int[] jumlahIsiPlaylist = new int[MAX_PLAYLIST];
    static int jumlahPlaylist = 0;

    static Scanner scanner = new Scanner(System.in);

    // ====== UTILITY INPUT ======
    static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                int v = Integer.parseInt(s.trim());
                return v;
            } catch (Exception e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

    // ====== METHOD MUSIC CRUD & OPERASI ======
    static void tambahMusic() {
        if (jumlahMusic >= MAX_MUSIC) {
            System.out.println("Kapasitas musik penuh!");
            return;
        }
        String t = readLine("Masukkan judul  : ");
        String a = readLine("Masukkan artis  : ");
        String d = readLine("Masukkan durasi : ");
        String al = readLine("Masukkan album  : ");

        judul[jumlahMusic] = t;
        artis[jumlahMusic] = a;
        durasi[jumlahMusic] = d;
        album[jumlahMusic] = al;
        playCount[jumlahMusic] = 0;
        jumlahMusic++;
        System.out.println("Musik berhasil ditambahkan!");
    }

    static void tampilkanSemuaMusic() {
        System.out.println("\n=== DAFTAR MUSIC ===");
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik.");
            return;
        }
        for (int i = 0; i < jumlahMusic; i++) {
            System.out.printf("%d. %s | %s | %s | %s | Diputar: %dx\n",
                    i + 1, judul[i], artis[i], durasi[i], album[i], playCount[i]);
        }
    }

    static void cariMusic() {
        String key = readLine("Masukkan kata kunci pencarian (judul/artis/album): ").toLowerCase();
        System.out.println("\n=== HASIL PENCARIAN ===");
        boolean found = false;
        for (int i = 0; i < jumlahMusic; i++) {
            if (judul[i].toLowerCase().contains(key) ||
                artis[i].toLowerCase().contains(key) ||
                album[i].toLowerCase().contains(key)) {
                System.out.printf("%d. %s | %s | %s | %s | Diputar: %dx\n",
                        i + 1, judul[i], artis[i], durasi[i], album[i], playCount[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ditemukan.");
        }
    }

    static void editMusic() {
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik untuk diedit.");
            return;
        }
        tampilkanSemuaMusic();
        int idx = readInt("Pilih nomor musik yang ingin diedit: ") - 1;
        if (idx < 0 || idx >= jumlahMusic) {
            System.out.println("Nomor tidak valid.");
            return;
        }
        String t = readLine("Masukkan judul  : ");
        String a = readLine("Masukkan artis  : ");
        String d = readLine("Masukkan durasi : ");
        String al = readLine("Masukkan album  : ");

        judul[idx] = t;
        artis[idx] = a;
        durasi[idx] = d;
        album[idx] = al;
        System.out.println("Data musik berhasil diubah.");
    }

    static void hapusMusic() {
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik untuk dihapus.");
            return;
        }
        tampilkanSemuaMusic();
        int idx = readInt("Pilih nomor musik yang ingin dihapus: ") - 1;
        if (idx < 0 || idx >= jumlahMusic) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        // Hapus dari semua playlist yang memuat index ini
        for (int p = 0; p < jumlahPlaylist; p++) {
            int newCount = 0;
            for (int i = 0; i < jumlahIsiPlaylist[p]; i++) {
                int val = isiPlaylist[p][i];
                if (val == idx) {
                    // skip (hapus)
                    continue;
                } else if (val > idx) {
                    // karena akan kita geser utama array music, index yang lebih besar harus dikurangi 1
                    isiPlaylist[p][newCount] = val - 1;
                    newCount++;
                } else {
                    isiPlaylist[p][newCount] = val;
                    newCount++;
                }
            }
            jumlahIsiPlaylist[p] = newCount;
            // set remaining slots to -1 (not necessary but neat)
            for (int k = newCount; k < MAX_MUSIC_PER_PLAYLIST; k++) isiPlaylist[p][k] = -1;
        }

        // Geser music arrays ke kiri mulai dari idx
        for (int i = idx; i < jumlahMusic - 1; i++) {
            judul[i] = judul[i + 1];
            artis[i] = artis[i + 1];
            durasi[i] = durasi[i + 1];
            album[i] = album[i + 1];
            playCount[i] = playCount[i + 1];
        }
        // clear last
        judul[jumlahMusic - 1] = null;
        artis[jumlahMusic - 1] = null;
        durasi[jumlahMusic - 1] = null;
        album[jumlahMusic - 1] = null;
        playCount[jumlahMusic - 1] = 0;
        jumlahMusic--;
        System.out.println("Musik berhasil dihapus (dan dihapus dari semua playlist).");
    }

    // ====== PLAYLIST OPERATIONS ======
    static void buatPlaylist() {
        if (jumlahPlaylist >= MAX_PLAYLIST) {
            System.out.println("Kapasitas playlist penuh!");
            return;
        }
        String nama = readLine("Masukkan nama playlist: ");
        namaPlaylist[jumlahPlaylist] = nama;
        jumlahIsiPlaylist[jumlahPlaylist] = 0;
        // init isiPlaylist row dengan -1
        for (int i = 0; i < MAX_MUSIC_PER_PLAYLIST; i++) isiPlaylist[jumlahPlaylist][i] = -1;
        jumlahPlaylist++;
        System.out.println("Playlist berhasil dibuat!");
    }

    static void tampilkanSemuaPlaylist() {
        System.out.println("\n=== DAFTAR PLAYLIST ===");
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist.");
            return;
        }
        for (int i = 0; i < jumlahPlaylist; i++) {
            System.out.printf("%d. %s | Jumlah lagu: %d\n", i + 1, namaPlaylist[i], jumlahIsiPlaylist[i]);
        }
    }

    static void tambahMusicKePlaylist() {
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist. Buat dulu.");
            return;
        }
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik. Tambah musik dulu.");
            return;
        }
        tampilkanSemuaPlaylist();
        int p = readInt("Pilih playlist (nomor): ") - 1;
        if (p < 0 || p >= jumlahPlaylist) {
            System.out.println("Playlist tidak valid.");
            return;
        }
        tampilkanSemuaMusic();
        int m = readInt("Pilih nomor musik yang ingin ditambahkan ke playlist: ") - 1;
        if (m < 0 || m >= jumlahMusic) {
            System.out.println("Musik tidak valid.");
            return;
        }
        if (jumlahIsiPlaylist[p] >= MAX_MUSIC_PER_PLAYLIST) {
            System.out.println("Playlist penuh!");
            return;
        }
        isiPlaylist[p][jumlahIsiPlaylist[p]] = m;
        jumlahIsiPlaylist[p]++;
        System.out.printf("Berhasil menambahkan \"%s\" ke playlist \"%s\".\n", judul[m], namaPlaylist[p]);
    }

    static void lihatIsiPlaylist() {
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist.");
            return;
        }
        tampilkanSemuaPlaylist();
        int p = readInt("Pilih playlist yang ingin dilihat (nomor): ") - 1;
        if (p < 0 || p >= jumlahPlaylist) {
            System.out.println("Playlist tidak valid.");
            return;
        }
        System.out.println("\n== Playlist: " + namaPlaylist[p] + " ==");
        if (jumlahIsiPlaylist[p] == 0) {
            System.out.println("Playlist kosong.");
            return;
        }
        for (int i = 0; i < jumlahIsiPlaylist[p]; i++) {
            int idx = isiPlaylist[p][i];
            if (idx >= 0 && idx < jumlahMusic) {
                System.out.printf("%d. %s | %s | %s\n", i + 1, judul[idx], artis[idx], durasi[idx]);
            }
        }
    }

    static void editPlaylist() {
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist.");
            return;
        }
        tampilkanSemuaPlaylist();
        int p = readInt("Pilih playlist yang ingin diedit (nomor): ") - 1;
        if (p < 0 || p >= jumlahPlaylist) {
            System.out.println("Playlist tidak valid.");
            return;
        }
        System.out.println("1. Ganti nama playlist");
        System.out.println("2. Hapus lagu dari playlist");
        System.out.println("3. Pindahkan urutan lagu (simple swap)");
        int pilihan = readInt("Pilih opsi: ");
        if (pilihan == 1) {
            String baru = readLine("Masukkan nama baru: ");
            namaPlaylist[p] = baru;
            System.out.println("Nama playlist diubah.");
        } else if (pilihan == 2) {
            if (jumlahIsiPlaylist[p] == 0) {
                System.out.println("Playlist kosong.");
                return;
            }
            lihatIsiPlaylistOne(p);
            int nomor = readInt("Pilih nomor lagu yang ingin dihapus: ") - 1;
            if (nomor < 0 || nomor >= jumlahIsiPlaylist[p]) {
                System.out.println("Nomor tidak valid.");
                return;
            }
            // geser
            for (int i = nomor; i < jumlahIsiPlaylist[p] - 1; i++) {
                isiPlaylist[p][i] = isiPlaylist[p][i + 1];
            }
            jumlahIsiPlaylist[p]--;
            isiPlaylist[p][jumlahIsiPlaylist[p]] = -1;
            System.out.println("Lagu dihapus dari playlist.");
        } else if (pilihan == 3) {
            if (jumlahIsiPlaylist[p] < 2) {
                System.out.println("Butuh minimal 2 lagu untuk menukar urutan.");
                return;
            }
            lihatIsiPlaylistOne(p);
            int a = readInt("Pilih nomor lagu pertama: ") - 1;
            int b = readInt("Pilih nomor lagu kedua  : ") - 1;
            if (a < 0 || a >= jumlahIsiPlaylist[p] || b < 0 || b >= jumlahIsiPlaylist[p]) {
                System.out.println("Nomor tidak valid.");
                return;
            }
            int tmp = isiPlaylist[p][a];
            isiPlaylist[p][a] = isiPlaylist[p][b];
            isiPlaylist[p][b] = tmp;
            System.out.println("Urutan lagu ditukar.");
        } else {
            System.out.println("Opsi tidak dikenal.");
        }
    }

    static void lihatIsiPlaylistOne(int p) {
        System.out.println("\n== Playlist: " + namaPlaylist[p] + " ==");
        for (int i = 0; i < jumlahIsiPlaylist[p]; i++) {
            int idx = isiPlaylist[p][i];
            if (idx >= 0 && idx < jumlahMusic) {
                System.out.printf("%d. %s | %s | %s\n", i + 1, judul[idx], artis[idx], durasi[idx]);
            }
        }
    }

    static void hapusPlaylist() {
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist.");
            return;
        }
        tampilkanSemuaPlaylist();
        int p = readInt("Pilih playlist yang ingin dihapus (nomor): ") - 1;
        if (p < 0 || p >= jumlahPlaylist) {
            System.out.println("Playlist tidak valid.");
            return;
        }
        // geser playlist arrays
        for (int i = p; i < jumlahPlaylist - 1; i++) {
            namaPlaylist[i] = namaPlaylist[i + 1];
            jumlahIsiPlaylist[i] = jumlahIsiPlaylist[i + 1];
            for (int j = 0; j < MAX_MUSIC_PER_PLAYLIST; j++) isiPlaylist[i][j] = isiPlaylist[i + 1][j];
        }
        // clear last row
        namaPlaylist[jumlahPlaylist - 1] = null;
        jumlahIsiPlaylist[jumlahPlaylist - 1] = 0;
        for (int j = 0; j < MAX_MUSIC_PER_PLAYLIST; j++) isiPlaylist[jumlahPlaylist - 1][j] = -1;
        jumlahPlaylist--;
        System.out.println("Playlist dihapus.");
    }

    // ====== PLAY OPERATIONS ======
    static void putarMusic() {
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik untuk diputar.");
            return;
        }
        tampilkanSemuaMusic();
        int idx = readInt("Pilih nomor musik yang ingin diputar: ") - 1;
        if (idx < 0 || idx >= jumlahMusic) {
            System.out.println("Nomor tidak valid.");
            return;
        }
        System.out.println("Memutar: " + judul[idx] + " ...");
        // simulasi selesai diputar
        playCount[idx]++;
        System.out.println("Selesai diputar. (Diputar total: " + playCount[idx] + " kali)");
    }

    static void putarPlaylist() {
        if (jumlahPlaylist == 0) {
            System.out.println("Belum ada playlist.");
            return;
        }
        tampilkanSemuaPlaylist();
        int p = readInt("Pilih playlist yang ingin diputar: ") - 1;
        if (p < 0 || p >= jumlahPlaylist) {
            System.out.println("Playlist tidak valid.");
            return;
        }
        if (jumlahIsiPlaylist[p] == 0) {
            System.out.println("Playlist kosong.");
            return;
        }
        System.out.println("Memutar playlist: " + namaPlaylist[p]);
        for (int i = 0; i < jumlahIsiPlaylist[p]; i++) {
            int idx = isiPlaylist[p][i];
            if (idx >= 0 && idx < jumlahMusic) {
                System.out.println("Memutar: " + judul[idx]);
                playCount[idx]++;
            }
        }
        System.out.println("Selesai memutar playlist.");
    }

    static void putarAcak() {
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik.");
            return;
        }
        int rand = (int) (Math.random() * jumlahMusic);
        System.out.println("Memutar acak: " + judul[rand] + " | " + artis[rand]);
        playCount[rand]++;
    }

    // ====== REKOMENDASI ======
    static void rekomendasiMusic() {
        if (jumlahMusic == 0) {
            System.out.println("Belum ada musik.");
            return;
        }
        // kita tampilkan top 3 dengan playCount tertinggi (bisa kurang jika data sedikit)
        int topN = 3;
        boolean[] used = new boolean[jumlahMusic];
        System.out.println("\n== Rekomendasi Musik (berdasarkan sering diputar) ==");
        int shown = 0;
        for (int r = 0; r < topN; r++) {
            int maxCount = -1;
            int idxMax = -1;
            for (int i = 0; i < jumlahMusic; i++) {
                if (!used[i] && playCount[i] > maxCount) {
                    maxCount = playCount[i];
                    idxMax = i;
                }
            }
            if (idxMax == -1 || maxCount == 0) break; // tidak ada lagi yang diputar
            System.out.printf("%d. %s | %s | Diputar: %dx\n", r + 1, judul[idxMax], artis[idxMax], playCount[idxMax]);
            used[idxMax] = true;
            shown++;
        }
        if (shown == 0) {
            System.out.println("Belum ada data putar. Coba putar beberapa lagu dulu agar rekomendasi muncul.");
        }
    }

    // ====== MENU & MAIN ======
    static void printMenu() {
        System.out.println("\n== ONLINE MUSIC PLAYER ==");
        System.out.println("1. Lihat semua musik");
        System.out.println("2. Cari musik");
        System.out.println("3. Tambah musik");
        System.out.println("4. Edit musik");
        System.out.println("5. Hapus musik");
        System.out.println("6. Buat playlist");
        System.out.println("7. Tambah musik ke playlist");
        System.out.println("8. Lihat playlist");
        System.out.println("9. Edit playlist");
        System.out.println("10. Hapus playlist");
        System.out.println("11. Putar musik");
        System.out.println("12. Putar playlist");
        System.out.println("13. Putar acak");
        System.out.println("14. Rekomendasi musik");
        System.out.println("0. Keluar");
    }

    public static void main(String[] args) {
        // contoh data awal (opsional) supaya saat pertama run ada sesuatu
        preloadSampleData();

        while (true) {
            printMenu();
            int pilihan = readInt("Pilih menu: ");
            switch (pilihan) {
                case 1: tampilkanSemuaMusic(); break;
                case 2: cariMusic(); break;
                case 3: tambahMusic(); break;
                case 4: editMusic(); break;
                case 5: hapusMusic(); break;
                case 6: buatPlaylist(); break;
                case 7: tambahMusicKePlaylist(); break;
                case 8: lihatIsiPlaylist(); break;
                case 9: editPlaylist(); break;
                case 10: hapusPlaylist(); break;
                case 11: putarMusic(); break;
                case 12: putarPlaylist(); break;
                case 13: putarAcak(); break;
                case 14: rekomendasiMusic(); break;
                case 0:
                    System.out.println("Keluar dari aplikasi...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak dikenal.");
            }
        }
    }

    static void preloadSampleData() {
        // tambahkan beberapa musik contoh supaya tidak kosong saat pertama run
        judul[0] = "Hati-Hati di Jalan"; artis[0] = "Tulus"; durasi[0] = "04:25"; album[0] = "Manusia"; playCount[0] = 2;
        judul[1] = "Love Story"; artis[1] = "Taylor Swift"; durasi[1] = "03:55"; album[1] = "Fearless"; playCount[1] = 5;
        judul[2] = "Lathi"; artis[2] = "Weird Genius"; durasi[2] = "03:33"; album[2] = "Single"; playCount[2] = 1;
        jumlahMusic = 3;
        // contoh playlist
        namaPlaylist[0] = "Santai Malam";
        jumlahIsiPlaylist[0] = 2;
        isiPlaylist[0][0] = 0;
        isiPlaylist[0][1] = 1;
        for (int i = 2; i < MAX_MUSIC_PER_PLAYLIST; i++) isiPlaylist[0][i] = -1;
        jumlahPlaylist = 1;
    }
}
