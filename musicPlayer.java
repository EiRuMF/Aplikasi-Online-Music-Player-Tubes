import java.util.Random;
import java.util.Scanner;

public class musicPlayer {

  static String music[][] = new String[1000][4];
  static Scanner input = new Scanner(System.in);

  static int jumlahMusic = 0;

  static String playlist[] = new String[10];
  static int banyakPlaylist = 0;

  static int playCount [] = new int [1000];

  static void tambahMusic(String judul, String artis, String durasi, String album) {

	    judul = judul.trim();
	    artis = artis.trim();
	    durasi = durasi.trim();
	    album = album.trim();

	    if (judul.isEmpty()) {
	        System.out.println("Judul tidak boleh kosong!");
	        return;
	    } else if (artis.isEmpty()) {
	        System.out.println("Artis tidak boleh kosong!");
	        return;
	    } else if (durasi.isEmpty()) {
	        System.out.println("Durasi tidak boleh kosong!");
	        return;
	    } else if (album.isEmpty()) {
	        System.out.println("Album tidak boleh kosong!");
	        return;
	    }

	    boolean juduldouble = false;
	    for (int i = 0; i < jumlahMusic; i++) {
	        if (music[i][0].equalsIgnoreCase(judul)) {
	            juduldouble = true;
	            break;
	        }
	    }

	    if (juduldouble) {
	        System.out.println("Judul yang Anda masukkan sudah ada!");
	    } else {
	        if (jumlahMusic < music.length) {
	            music[jumlahMusic][0] = judul;
	            music[jumlahMusic][1] = artis;
	            music[jumlahMusic][2] = durasi;
	            music[jumlahMusic][3] = album;
	            jumlahMusic++;
	            System.out.println("Musik '" + judul + "' berhasil ditambahkan!");
	        } else {
	            System.out.println("Penyimpanan musik penuh.");
	        }
	    }
	}

  static void tampilkanMusic() {
	    if (jumlahMusic == 0) {
	        System.out.println("Belum ada musik");
	    } else {
	        System.out.println("\n--- DAFTAR MUSIK ---");
	        System.out.printf("%-3s %-25s %-20s %-10s %-25s\n", "No", "Judul", "Artis", "Durasi", "Album");
	        System.out.println("---------------------------------------------------------------------");
	        for (int i = 0; i < jumlahMusic; i++) {
	            System.out.printf("%-3d %-25s %-20s %-10s %-25s\n",
	                    (i + 1), music[i][0], music[i][1], music[i][2], music[i][3]);
	        }
	        System.out.println("---------------------------------------------------------------------");
	    }
	}

  static void editDataMusic(int nomorMusic,int pilihanNomorEdit,String edit) {

    edit = edit.trim();
    if(edit.isEmpty()) {
        System.out.println("Input tidak boleh kosong!");
        return;
    }

    String dataLama = "";
    if(pilihanNomorEdit == 1) dataLama = music[nomorMusic][0];
    else if(pilihanNomorEdit == 2) dataLama = music[nomorMusic][1];
    else if(pilihanNomorEdit == 3) dataLama = music[nomorMusic][2];
    else if(pilihanNomorEdit == 4) dataLama = music[nomorMusic][3];
    else {
        System.out.println("Pilihan salah!");
        return;
    } 

    if(edit.equalsIgnoreCase(dataLama)) {
        System.out.println("Input sama dengan data sebelumnya, tidak ada perubahan.");
        return;
    }

    if(pilihanNomorEdit == 1) {
        for(int i = 0; i < jumlahMusic; i++) {
            if(i != nomorMusic && music[i][0].equalsIgnoreCase(edit)) {
                System.out.println("Judul yang Anda masukkan sudah ada!");
                return;
            }
        }
        music[nomorMusic][0] = edit;
    } else if(pilihanNomorEdit == 2) {
        music[nomorMusic][1] = edit;
    } else if(pilihanNomorEdit == 3) {
        music[nomorMusic][2] = edit;
    } else if(pilihanNomorEdit == 4) {
        music[nomorMusic][3] = edit;
    } else {
        System.out.println("Pilihan salah!");
        return;
    }

    System.out.println("Data berhasil diubah!");
}

  static void hapusDataMusic(int nomorMusic) {
    String judulHapus = music[nomorMusic][0];
    for (int i = nomorMusic; i < jumlahMusic - 1; i++) {
    	for(int j =0;j<4;j++){
        music[i][j]=music[i+1][j];
      }
    }
    
    jumlahMusic--;
    System.out.println("Musik '" + judulHapus + "' berhasil dihapus!");
  }

  static void tampilkanPlaylist() {
    if (banyakPlaylist == 0) {
      System.out.println("Belum ada playlist.");
    } else {
      System.out.println("\n--- DAFTAR PLAYLIST ---");
      for (int i = 0; i < banyakPlaylist; i++) {
        System.out.println((i + 1) + ". " + playlist[i]);
      }
    }
  }

  static void tambahPlaylist() {
    if (banyakPlaylist >= playlist.length) {
      System.out.println("Playlist Penuh (Max 10)");
      return;
    } else {
      System.out.print("Masukkan nama playlist: ");
      String addNamaPlaylist = input.nextLine();

      playlist[banyakPlaylist] = addNamaPlaylist;
      banyakPlaylist++;

      System.out.println("Playlist \"" + addNamaPlaylist + "\" berhasil ditambahkan!\n");
    }
  }

  static void hapusDataPlaylist(String namaPlaylist) {
    System.out.println("Fungsi hapus playlist untuk '" + namaPlaylist + "' belum diimplementasikan sepenuhnya.");
  }

  static void cariMusik() {
    System.out.println("\n=== CARI MUSIK ===");
    System.out.print("Masukkan kata kunci (judul/artis/album): ");

    String keyword = input.nextLine().trim().toLowerCase();

  //   boolean ditemukan = false;

    System.out.println("\n--- HASIL PENCARIAN ---");
    for (int i = 0; i < jumlahMusic; i++) {
      String judul = music[i][0];
      String artis = music[i][1];
      String album = music[i][3];

      if (judul.toLowerCase().contains(keyword) ||
          artis.toLowerCase().contains(keyword) ||
          album.toLowerCase().contains(keyword)) {

        System.out.printf("%d. %s | %s | %s | %s\n",
            (i + 1), judul, artis, music[i][2], album);
        ditemukan = true;
      }
    }

    if (!ditemukan) {
      System.out.println("Musik dengan kata kunci '" + keyword + "' tidak ditemukan.");
    }
  }

  static void play(int nomorMusic) {
    if (nomorMusic >= 0 && nomorMusic < jumlahMusic) {
      playCount[nomorMusic]++;

      System.out.println("\n\t SEDANG DIPUTAR ");
      System.out.println("Judul: " + music[nomorMusic][0]);
      System.out.println("Artis: " + music[nomorMusic][1]);
      System.out.println("Durasi: " + music[nomorMusic][2]);
      System.out.println("Album: " + music[nomorMusic][3]);
      System.out.println("------------------------------------");
    } else {
      System.out.println("Nomor musik tidak valid.");
    }
  }

  static void shufflePlay() {
    if (jumlahMusic == 0) {
      System.out.println("Tidak ada musik untuk di-shuffle.");
      return;
    }

    Random random = new Random();
    int indeksAcak = random.nextInt(jumlahMusic);

    System.out.println("\n\t SHUFFLE PLAY ");
    System.out.println("\t MEMUTAR SECARA ACAK ");
    System.out.println("Judul: " + music[indeksAcak][0]);
    System.out.println("Artis: " + music[indeksAcak][1]);
    System.out.println("Durasi: " + music[indeksAcak][2]);
    System.out.println("Album: " + music[indeksAcak][3]);
    System.out.println("------------------------------------");
  }

  static void rekomendasiMusic() {
    if (jumlahMusic == 0) {
        System.out.println("Tidak ada musik untuk direkomendasikan.");
        return;
    }

    int maxPlay = 0;
    int indexRekomendasi = 0;

    for (int i = 0; i < jumlahMusic; i++) {
        if (playCount[i] > maxPlay) {
            maxPlay = playCount[i];
            indexRekomendasi = i;
        }
    }

    System.out.println("\n\t REKOMENDASI MUSIK HARI INI ");
    System.out.println("Berdasarkan musik yang sering Anda putar:");
    System.out.println("Judul : " + music[indexRekomendasi][0]);
    System.out.println("Artis : " + music[indexRekomendasi][1]);
    System.out.println("Durasi: " + music[indexRekomendasi][2]);
    System.out.println("Album : " + music[indexRekomendasi][3]);
    System.out.println("------------------------------------");
}


  public static void main(String[] args) {

    tambahMusic("Rasa Sayange", "Duo Ambon", "3:15", "Lagu Daerah");
    tambahMusic("Aiya Susanti", "Mei Mei", "2:40", "Kartun Anak");

    int pilihan;
    do {
      System.out.println("\n========================");
      System.out.println("==ONLINE MUSIC PLAYER==");
      System.out.println("========================");
      System.out.println("0. Keluar");
      System.out.println("1. Lihat semua musik");
      System.out.println("2. Cari musik");
      System.out.println("3. Tambah musik");
      System.out.println("4. Edit musik");
      System.out.println("5. Hapus musik");
      System.out.println("6. Putar Musik (Play)");
      System.out.println("7. Shuffle Play");
      System.out.println("8. Tampilkan Playlist");
      System.out.println("9. Tambah Playlist");
      System.out.println("10. Rekomendasi Musik");
      System.out.print("Pilih menu: ");

      if (input.hasNextInt()) {
        pilihan = input.nextInt();
        input.nextLine();
      } else {
        System.out.println("Input tidak valid. Silakan masukkan angka.");
        input.nextLine();
        pilihan = -1;
        continue;
      }

      if (pilihan == 1) {
        tampilkanMusic();
      } else if (pilihan == 2) {
        cariMusik();
      } else if(pilihan ==3) {
	    System.out.print("Masukkan judul : ");
	    String judul = input.nextLine();
	    System.out.print("Masukkan artis : ");
	    String artis = input.nextLine();
	    System.out.print("Masukkan durasi : ");
	    String durasi = input.nextLine();
	    System.out.print("Masukkan album : ");
	    String album = input.nextLine();
	    tambahMusic(judul, artis, durasi, album);
	  } else if(pilihan ==4) {
	    	  tampilkanMusic();
	    	  
	    	  if(jumlahMusic!=0) {
	    		  System.out.println("\n=== EDIT MUSIK ===");
		    	  System.out.println("Pilih nomor musik yang ingin diubah : ");
		    	  int nomorMusic = input.nextInt()-1;
		    	  
		    	  if(nomorMusic>=0 && nomorMusic < jumlahMusic) {
			    	  System.out.println("1. Judul");
			    	  System.out.println("2. Artis");
			    	  System.out.println("3. Durasi");
			    	  System.out.println("4. Album");
			    	  
			    	  System.out.println("Pilih yang ingin anda ubah : ");
			    	  int nomorEdit = input.nextInt();
			    	  input.nextLine();
			    	  if(nomorEdit>0 && nomorEdit<=4) {
				    	  System.out.println("Masukkan perubahan : ");
				    	  String ubah = input.nextLine();
				    	  editDataMusic(nomorMusic,nomorEdit , ubah);
			    	  }else {
			    		  System.out.println("Pilihan yang Anda masukkan salah");
			    	  }  
		    	  }else {
		    		  System.out.println("Nomor musik yang anda masukkan salah");
		    	  }
	    	  }
	      } else if(pilihan ==5) {
	    	  tampilkanMusic();
	    	  System.out.println(" ");
	    	  if(jumlahMusic!=0) {
		    	  System.out.println("Pilih nomor musik mana yang anda ingin hapus : ");
			      int nomorMusicHapus = input.nextInt()-1;
			      if(nomorMusicHapus>=0 && nomorMusicHapus<jumlahMusic) {
			    	  hapusDataMusic(nomorMusicHapus);
			      }else {
			    	  System.out.println("Nomor musik yang Anda masukkan salah");
			      }
		      }
	      }
       else if (pilihan == 6) {
        tampilkanMusic();
        if (jumlahMusic == 0)
          continue;
        System.out.print("Pilih nomor musik yang ingin diputar: ");
        int nomorPlay = input.nextInt() - 1;
        input.nextLine();
        play(nomorPlay);
      } else if (pilihan == 7) {
        shufflePlay();
      } else if (pilihan == 8) {
        tampilkanPlaylist();
      } else if (pilihan == 9) {
        tambahPlaylist();
      } else if (pilihan == 10) {
        rekomendasiMusic();
      }

    } while (pilihan != 0);

    System.out.println("Keluar dari aplikasi");

  }
}