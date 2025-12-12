import java.util.Scanner;

public class musicPlayer {

  static String music[][] = new String[1000][4];
  String playlist[] = new String[10];
  static Scanner input = new Scanner(System.in);

  // cari musik
  int jumlahMusic = 0;

  // PlayList
  int banyakPlaylist = 0;


  //Bagian musik

  static void tambahMusic(String judul, String artis, String durasi, String album) {

    music[indexMusic][0] = judul;
    music[indexMusic][1] = artis;
    music[indexMusic][2] = durasi;
    music[indexMusic][3] = album;
    jumlahMusic++;

  }

  static void tampilkanMusic() {
    if (jumlahMusic == 0) {
      System.out.println("Belum ada musik");
    } else {
      for (int i = 0; i < jumlahMusic; i++) {
        System.out.println((i+1)+". "+music[i][0] + "|" + music[i][1] + "|" + music[i][2] + "|" + music[i][3]);
      }
    }
  }

  static void editDataMusic(int nomorMusic,int pilihan,String edit) {
	  if(pilihan ==1) {
		  music[nomorMusic][0]=edit;
	  }else if(pilihan ==2) {
		  music[nomorMusic][1]=edit;
	  }else if(pilihan ==3) {
		  music[nomorMusic][2]=edit;
	  }else if(pilihan ==4) {
		  music[nomorMusic][3]=edit;
	  }else {
		  System.out.println("Anda memasukkan nomor yang salah ");
	  }   
  }

  static void hapusDataMusic(int nomorMusic){
	  for(int i=nomorMusic;i<jumlahMusic-1;i++) {
		  music[i][0]=music[i+1][0];
		  music[i][1]=music[i+1][1];
		  music[i][2]=music[i+1][2];
		  music[i][3]=music[i+1][3];
		  
	  }
	  jumlahMusic--;
  }

  

  // Bagian Playlist

  static void tambahPlaylist(String music) {
    if (banyakPlaylist <= 10) {
      System.out.println("Playlist Penuh");
      return;
    } else {
      System.out.println("Masukkan nama playlist: ");
      String addNamaPlaylist = input.nextString();

        namaPlaylist[banyakPlaylist] = nama;
        banyakPlaylist

        banyakPlaylist++;
      namaPlaylist[jumlahPlaylist] = nama;
      banyakPlaylist++;
      }
  }

  static void tampilkanPlaylist(String tambahPlaylist) {
  }
  
  static void editDataPlaylist(String tambahPlaylist) {
  }

  static void hapusDataPlaylist(String tambahPlaylist) {
  }

  public void cariMusik() {
    System.out.println("\n=== CARI MUSIK (OOP) ===");
    System.out.print("Masukkan kata kunci (judul/artis/album): ");

    if (input.hasNextLine()) {
      input.nextLine();
    }
    String keyword = input.nextLine().trim().toLowerCase();

    boolean ditemukan = false;

    for (int i = 0; i < jumlahMusic; i++) {
      Music m = music[i];

      if (m.judul.toLowerCase().contains(keyword) ||
          m.artis.toLowerCase().contains(keyword) ||
          m.album.toLowerCase().contains(keyword)) {
        System.out.println("--- Ditemukan! ---");
        System.out.println((i + 1) + ". " + m.toString());
        ditemukan = true;
      }
    }

    if (!ditemukan) {
      System.out.println("Musik dengan kata kunci '" + keyword + "' tidak ditemukan.");
    }
  }
  
  static void play(String music){
  }

  static void shufflePlay(String rekomendasi){
  }

  static void rekomendasiMusic(String tampilkanPlaylist){
  }
  




















  public static void main(String[] args) {

		int pilihan=0;
	    do {
	      System.out.println("  ");
	      System.out.println("==ONLINE MUSIC PLAYER==");
	      System.out.println("0. Keluar");
	      System.out.println("1. Lihat semua musik");
	      System.out.println("2. Cari musik");
	      System.out.println("3. Tambah musik");
	      System.out.println("4. Edit musik");
	      System.out.println("5. Hapus musik");
	      pilihan = input.nextInt();
	      input.nextLine();
	      
	      if(pilihan ==1) {
	    	  tampilkanMusic();
	      }else if(pilihan ==2) {
	    	  
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
	    	  int nomor = input.nextInt()-1;
	    	  System.out.println("1. Judul");
	    	  System.out.println("2. Artis");
	    	  System.out.println("3. Durasi");
	    	  System.out.println("4. Album");
	    	  
	    	  System.out.println("Pilih yang ingin anda ubah : ");
	    	  int nomorEdit = input.nextInt();
	    	  input.nextLine();
	    	  System.out.println("Masukkan perubahan : ");
	    	  String ubah = input.nextLine();
	    	  editDataMusic(nomor,nomorEdit , ubah);
	      }else if(pilihan ==5) {
	    	  tampilkanMusic();
	    	  System.out.println(" ");
	    	  System.out.println("Pilih nomor musik mana yang anda ingin hapus : ");
	    	  int hapus = input.nextInt()-1;
	    	  hapusDataMusic(hapus);
	      }
	        
	    } while (pilihan !=0);
	    {
	      System.out.println("Keluar dari aplikasi");
	    }

  }
}