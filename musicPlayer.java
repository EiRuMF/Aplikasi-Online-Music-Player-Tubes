import java.util.Random;
import java.util.Scanner;

public class musicPlayer {

  static String music[][] = new String[1000][4];
  static Scanner input = new Scanner(System.in);

  // cari musik
  static int jumlahMusic = 0;
  static int playCount [] = new int [1000];

  // PlayList
  static String playlist[] = new String[10];
  static int playlistMusic[][] = new int[10][50];
  static int jumlahMusicPlaylist[] = new int[10];
  static int banyakPlaylist = 0;
  


  //Bagian musik

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

  // Bagian Playlist

  static void tambahPlaylist() {
    if (banyakPlaylist >= 10) {
      System.out.println("Playlist Penuh");
      return;
    } else {
      System.out.println("Masukkan nama playlist: ");
      String nama = input.nextLine();

      playlist[banyakPlaylist] = nama;
      banyakPlaylist++;

      System.out.println("Playlist \"" + nama + "\" berhasil ditambahkan!\n");
    }
  }

  static void tampilkanPlaylist(){
    if(banyakPlaylist == 0){
      System.out.println("Tidak ada playlist");
      return;
    }

    for(int i = 0; i < banyakPlaylist; i++){
      System.out.println((i + 1) + ". " + playlist[i] +
      "(" + jumlahMusicPlaylist[i] + " lagu)");
    }
  }

  static void tambahMusicKePlaylist() {

    if (banyakPlaylist == 0) {
        System.out.println("Belum ada playlist");
        return;
    }

    if (jumlahMusic == 0) {
        System.out.println("Belum ada musik");
        return;
    }

    tampilkanPlaylist();
    System.out.print("Pilih playlist: ");
    int p = input.nextInt() - 1;

    if (p < 0 || p >= banyakPlaylist) {
        System.out.println("Playlist tidak valid");
        input.nextLine();
        return;
    }

    tampilkanMusic();
    System.out.print("Pilih musik: ");
    int m = input.nextInt() - 1;

    if (m < 0 || m >= jumlahMusic) {
        System.out.println("Musik tidak valid");
        input.nextLine();
        return;
    }

      playlistMusic[p][jumlahMusicPlaylist[p]] = m;
    jumlahMusicPlaylist[p]++;

    input.nextLine();
    System.out.println("Musik berhasil ditambahkan ke playlist");
    }

    

  static void lihatIsiPlaylist() {

    if (banyakPlaylist == 0) {
        System.out.println("Belum ada playlist");
        return;
    }

    tampilkanPlaylist();
    System.out.print("Pilih playlist: ");
    int p = input.nextInt() - 1;

    if (p < 0 || p >= banyakPlaylist) {
        System.out.println("Playlist tidak valid");
        input.nextLine();
        return;
    }

    if (jumlahMusicPlaylist[p] == 0) {
        System.out.println("Playlist masih kosong");
        input.nextLine();
        return;
    }

    System.out.println("Isi playlist \"" + playlist[p] + "\":");

    for (int i = 0; i < jumlahMusicPlaylist[p]; i++) {
        int idx = playlistMusic[p][i];
        System.out.println((i + 1) + ". " +
            music[idx][0] + " -- " + music[idx][1] + " -- " + music[idx][2] + " -- " + music[idx][3]);
    }

    input.nextLine();
}

static void hapusPlaylist() {

    if (banyakPlaylist == 0) {
        System.out.println("Belum ada playlist");
        return;
    }

    tampilkanPlaylist();
    System.out.print("Pilih playlist yang ingin dihapus: ");
    int p = input.nextInt() - 1;
    input.nextLine();

    if (p < 0 || p >= banyakPlaylist) {
        System.out.println("Playlist tidak valid");
        return;
    }

    String namaHapus = playlist[p];

    // Geser playlist
    for (int i = p; i < banyakPlaylist - 1; i++) {
        playlist[i] = playlist[i + 1];
        jumlahMusicPlaylist[i] = jumlahMusicPlaylist[i + 1];

        for (int j = 0; j < 50; j++) {
            playlistMusic[i][j] = playlistMusic[i + 1][j];
        }
    }

    // Bersihkan data terakhir (opsional tapi rapi)
    playlist[banyakPlaylist - 1] = null;
    jumlahMusicPlaylist[banyakPlaylist - 1] = 0;

    for (int j = 0; j < 50; j++) {
        playlistMusic[banyakPlaylist - 1][j] = 0;
    }

    banyakPlaylist--;

    System.out.println("Playlist \"" + namaHapus + "\" berhasil dihapus!");
}


  

  //static void hapusDataPlaylist(String tambahPlaylist) {
  //}

  static void cariMusik() {
	    System.out.println("\n=== CARI MUSIK ===");
	    System.out.print("Masukkan kata kunci (judul/artis/album): ");

	    String keyword = input.nextLine().trim().toLowerCase();

	    boolean ditemukan = false;

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
      playCount[indeksAcak]++;

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

		int pilihan;
	    do {
	      System.out.println("  ");
	      System.out.println("==ONLINE MUSIC PLAYER==");
	      System.out.println("0. Keluar");
	      System.out.println("1. Lihat semua musik");
	      System.out.println("2. Cari musik");
	      System.out.println("3. Tambah musik");
	      System.out.println("4. Edit musik");
	      System.out.println("5. Hapus musik");
        System.out.println("6. Tambah playlist");
        System.out.println("7. Tampilkan playlist");
        System.out.println("8. Tambah music ke playlist ");
        System.out.println("9. Lihat isi playlist ");
        System.out.println("10. Hapus playlist ");
        System.out.println("11. Putar Musik");
        System.out.println("12. Shuffle Play");
        System.out.println("13. Rekomendasi Musik");
	      pilihan = input.nextInt();
	      input.nextLine();
	      
	      if(pilihan ==1) {
	    	  tampilkanMusic();
	      }else if(pilihan ==2) {
	    	  cariMusik();
	      }else if(pilihan ==3) {
	    	  System.out.print("Masukkan judul : ");
	    	  String judul = input.nextLine();
	    	  System.out.print("Masukkan artis : ");
	    	  String artis = input.nextLine();
	    	  System.out.print("Masukkan durasi : ");
	    	  String durasi = input.nextLine();
	    	  System.out.print("Masukkan album : ");
	    	  String album = input.nextLine();
	    	  tambahMusic(judul, artis, durasi, album);
	      }else if(pilihan ==4) {
	    	  tampilkanMusic();
	    	  System.out.println("Pilih nomor musik : ");
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
	      }else if(pilihan ==5) {
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
	      } else if(pilihan == 6) {
          tambahPlaylist();
        } else if(pilihan == 7) {
          tampilkanPlaylist();
        } else if (pilihan == 8) {
          tambahMusicKePlaylist();
        } else if (pilihan == 9) {
          lihatIsiPlaylist();
        } else if (pilihan == 10) {
          hapusPlaylist();
        } else if(pilihan ==11){
          tampilkanMusic();
	        if (jumlahMusic == 0)
	          continue;
	        System.out.print("Pilih nomor musik yang ingin diputar: ");
	        int nomorPlay = input.nextInt() - 1;
	        input.nextLine();
	        play(nomorPlay);
        } else if(pilihan ==12){
          shufflePlay();
        } else if(pilihan == 13){
          rekomendasiMusic();
        }else{
          System.out.println("Pilihan yang Anda masukkan salah!");
        }
	        
	    } while (pilihan !=0);
	    
	      System.out.println("Keluar dari aplikasi");

  }
}