/* HANS ZIMMER - INTERSTELLAR "DAY ONE"  */

/* Rythmes */

let dc=3;
let c=6;
let n=12;
let b=24;
let bp=36;
let dbp=72;

/* Gammes */

let scale1=scale e phrygian;

/* Instruments */ 

let instru1 = instru piano;
let instru2 = ens instru1 (instru string 30);
let organ = instru churchorgan;

/* Phrases */

let intro = repeat 11 1cn;

let p1_1 = chord (repeat 9 1cn) (chord { 4bbp , 5bdbp } { 4cbp , 5cdbp });
let p1_2 = chord (repeat 14 1cn) (chord { 4bn , 5bn , 6bn , 5bn , 4bn , 5bn , 6bbp , 5b60 }
	{ 4cn , 5cn , 6cn , 5cn , 4cn , 5cn , 6cbp , 5c60 });
let p1_3 = chord (repeat 11 1cn) (chord { 4bbp , 6bbp , 5b60 } 
	{ 4cb , 1dn , 6cbp , 5c60 });
let p1_3 = chord (repeat 11 1cn) (chord { 4bbp , 6bbp , 5b60 } 
	{ 4cb , 1dn , 6cbp , 5c60 });
let p1_4 = chord (repeat 15 1cn) (chord { 4bn , ?n , 6bn , 5bn , 4bn , 5bn , 6bbp , 5bdbp }
	{ 4cn , 1dn , 6cn , 5cn , 4cn , 5cn , 6cbp , 5cdbp });
let p1_5 = chord (repeat 15 1cn) (chord { 4bn , ?n , 6bn , 5bn , 4bn , 5bn , 6bbp , 7bbp, 1dbp }
	{ 4cn , 1dn , 6cn , 5cn , 4cn , 5cn , 6cbp , 7cbp , 1cbp });
	
let p2_1 = chord
	{ 4abp , 5abp , 5abp } (chord
		{ 4bn , 4bn, 4bn, 5bn , 5bn , 5bn , 5bn , 5bn , 5bn }
		{ 4ddc , 1ddc , 5ddc , 1ddc , 6ddc , 1ddc , 5ddc , 1ddc , 4ddc , 1ddc , 6ddc , 1ddc , 5ddc , 1ddc , 4ddc , 1ddc , 3ddc , 1ddc , 4ddc , 1ddc , 5ddc , 1ddc , 1edc , 1ddc , 5ddc , 1ddc , 4ddc , 1ddc , 3ddc , 1ddc , 4ddc , 1ddc , 5ddc , 1ddc , 1edc, 1ddc });
let p2_2 = chord
		(chord { 4an , 5an , 6an, 5an , 4an, 5an , 6abp, 5abp, 5abp } { 4bn , 5bn , 6bn, 5bn , 4bn, 5bn , 6bn, 6bn, 6bn, 5bn, 5bn, 5bn , 5bn, 5bn, 5bn })
		{ 4ddc , 1ddc , 1edc , 1ddc , 5ddc , 1ddc , 1edc , 1ddc , 6ddc , 1ddc , 1edc , 1ddc , 5ddc , 1ddc , 1edc , 1ddc , 4ddc , 1ddc , 1edc , 1ddc , 5ddc , 1ddc , 1edc , 1ddc , 6ddc , 1ddc , 7ddc , 1ddc, 6ddc , 1ddc, 5ddc , 1ddc, 4ddc , 1ddc , 6ddc , 1ddc , 5ddc , 1ddc, 4ddc , 1ddc, 3ddc , 1ddc, 4ddc , 1ddc, 5ddc , 1ddc , 1edc , 1ddc ,5ddc , 1ddc, 4ddc , 1ddc, 3ddc , 1ddc, 4ddc , 1ddc, 5ddc , 1ddc , 1edc , 1ddc};
		
let p2_3_basse = {{ 4abp , 6abp , 5abp , 5abp },
					{ 4bn , 4bn , 4bn , 6bn , 6bn , 6bn , 5bn , 5bn , 5bn , 5bn , 5bn , 5bn }};
let p2_3_theme = { 4ddc , 1ddc, 4ddc, 1ddc, 4ddc, 1ddc, 4ddc, 1ddc , 1edc , 1ddc , 4ddc, 1ddc , 6ddc , 1ddc , 5ddc , 1ddc, 4ddc , 1ddc, 5ddc , 1ddc, 6ddc , 1ddc , 4ddc , 1ddc , 5ddc , 1ddc, 4ddc , 1ddc, 3ddc , 1ddc, 4ddc , 1ddc, 5ddc , 1ddc , 1edc , 1ddc ,5ddc , 1ddc, 4ddc , 1ddc, 3ddc , 1ddc, 4ddc , 1ddc, 5ddc , 1ddc , 1edc , 1ddc};
let p2_3 = chord p2_3_basse p2_3_theme;		

let p2_4_basse = {{ 4ab , 6an, 5an, 4an, 5an, 6abp, 5abp, 5abp},
				{ 4bn , 4bn, 6bn, 5bn, 4bn, 5bn, 6bn, 6bn, 6bn, 5bn, 5bn, 5bn, 5bn, 5bn, 5bn}};
let p2_4_theme = { 4ddc , 1ddc, 4ddc, 1ddc, 1edc, 1ddc, 4ddc, 1ddc, 6ddc, 1ddc, 1edc, 1ddc,
					5ddc, 1ddc, 1edc, 1ddc, 4ddc, 1ddc, 1edc, 1ddc, 5ddc, 1ddc, 1edc, 1ddc,
					6ddc, 1ddc, 7ddc, 1ddc, 6ddc, 1ddc, 5ddc, 1ddc, 4ddc, 1ddc, 6ddc, 1ddc,
					5ddc, 1ddc, 4ddc, 1ddc, 3ddc, 1ddc, 4ddc, 1ddc, 5ddc, 1ddc, 1edc, 1ddc,
					5ddc, 1ddc, 4ddc, 1ddc, 3ddc, 1ddc, 4ddc, 1ddc, 5ddc, 1ddc, 1edc, 1ddc};
let p2_4 = chord p2_4_basse p2_4_theme;	

let p2_5_basse = {{ 4ab , 6an, 5an, 4an, 5an, 6abp, 7abp, 1abp, 1abp , 1abp, 1abp },
					{ 4bn , 4bn, 6bn, 5bn, 4bn, 5bn, 6bn, 6bn, 6bn, 7bn, 7bn, 7bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn, 1bn}};
let p2_5_theme = { 4ddc , 1ddc, 4ddc, 1ddc, 1edc, 1ddc, 4ddc, 1ddc, 6ddc, 1ddc, 1edc, 1ddc,
					5ddc, 1ddc, 1edc, 1ddc, 4ddc, 1ddc, 1edc, 1ddc, 5ddc, 1ddc, 1edc, 1ddc,
					6ddc, 1ddc, 7ddc, 1ddc, 6ddc, 1ddc, 5ddc, 1ddc, 4ddc, 1ddc, 6ddc, 1ddc,
					7ddc, 1ddc, 7ddc, 1ddc, 7ddc, 1ddc, 7ddc, 1ddc, 7ddc, 1ddc, 7ddc, 1ddc,
					1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc,
					1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc,
					1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc,
					1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1edc, 1ddc, 1ec};
let p2_5 = chord p2_5_basse p2_5_theme;
		
let p3_1 = { { 2bdbp } , { 6bdbp } , { 2cdbp } , 4cn ,
			 1db ,
			 4cn ,
			 1db ,
			 { 3bbp } , { 1cdbp } , { 3cdbp } , 5cn ,
			 1db ,
			 5cn ,
			 1db ,
			 { 4bbp } , { 1cdbp } , { 4cdbp } , 6cn ,
			 1db ,
			 6cn ,
			 1db ,
			  { 3bbp } , { 1cdbp } , { 3cdbp } , 7cn ,
			 1db ,
			 7cn ,
			 1dn ,
			 5cn } ;
let p3_2 = { { 2bdbp } , { 6bdbp } , { 2cdbp } , 4cn ,
			 1db ,
			 4cn ,
			 1dn ,
			 4cn ,
			 { 3bbp } , { 1cdbp } , { 3cdbp } , 5cn ,
			 1db ,
			 5cn ,
			 1db ,
			 { 4bbp } , { 1cdbp } , { 4cdbp } , 6cn ,
			 1db ,
			 6cn ,
			 1db ,
			  { 3bbp } , { 1cdbp } , { 3cdbp } , 7cn ,
			 1db ,
			 { 7cn } , 7dn ,
			 { 1dn } , 1en ,
			 { 5cn } , 5dn } ;

let p3_3_theme = { 4cn , 1db, 4cn, 1db, 5cn, 1db, 5cn, 1db, 6cn, 1db, 6cn, 1db, 7cn, 1db, 7cn, 1db, 1db};
let p3_3_theme_2 = { 6cn , ?b , ?n , ?b, 1cn, ?b, ?n, ?b, 1cn, ?b, ?n, ?b, 1cn, ?b, ?n, ?b, ?b, (repeat 7 1dn)}; 
let p3_3_basse = { { 6bbp } , { 2cbp } , 2bb ,
					2bn ,
					{ 6bbp } , { 2cbp } , 2bb ,
					2bn , 
					{ 1cbp } , { 3cbp } , 3bb ,
					3bn ,
					{ 1cbp } , { 3cbp } , 3bb ,
					3bn ,
					{ 1cbp } , { 4cbp } , 4bb ,
					4bn ,
					{ 1cbp } , { 4cbp } , 4bb ,
					4bn ,
					{ 1cbp } , { 3cbp } , 3bb ,
					3bn ,
					{ 1cb } , { 3cb } , 3bb ,
					{ 2bn } , 2cn, 
					{ 1bdbp } , 1cdbp };
					
let p3_3 = chord p3_3_basse (chord p3_3_theme_2 (chord p3_3_theme transpose 7 p3_3_theme));	

let p4_1_basse = { (repeat 2 {4bn , 1cn , 6cn}) , (repeat 2 {3bn , 1cn , 5cn})}; 
let p4_1_theme = { (repeat 12 {{ 4ddc } , 6ddc , 1ddc}), (repeat 12 {{ 3ddc } , { 5ddc }, 7ddc , 1ddc}) };
let p4_1 = chord p4_1_theme p4_1_basse;		 
			 
let p4_2_basse = { (repeat 2 {2bn , 6bn , 4cn}) , (repeat 2 {3bn , 1cn , 5cn})};
let p4_2_theme = { (repeat 12 {{ 4ddc } , { 6ddc } , 1ddc , 6cdc }), (repeat 12 {{ 3ddc } , { 5ddc }, 7ddc , 5cdc}) };
let p4_2 = chord p4_2_theme p4_2_basse;

let p4_3_basse = { (repeat 2 { {4an}, 4bn , 4bn , {4cn} , 6cn }) , 
				{{3an} , 3bn , 3bn, {3cn} , 5cn, {3an} , 3bn , {3cn} , 5cn, 2cn }};
let p4_3_theme = { (repeat 12 {{ 4ddc } , { 6ddc } , 1edc , 1ddc }), (repeat 6 {{ 3ddc } , { 6ddc }, 1edc , 1ddc}), (repeat 6 {{ 3ddc } , { 5ddc }, 7ddc , 1ddc}) };
let p4_3 = chord p4_3_theme p4_3_basse;

let outro_organ = { {1b128},{1a128}, {1db}, {3db}, 1eb };
let outro_piano = { ?b , repeat 8 1cn , 1cb };
  
		
/* Body */

begin
tempo 200;
play scale1 instru1 intro;
play scale1 instru1 p1_1;
play scale1 instru2 p1_2;
play scale1 instru1 p1_3;
play scale1 instru2 p1_4;
play scale1 instru1 p1_5;
play scale1 organ p2_1;
play scale1 organ p2_2;
play scale1 (ens organ instru2) p2_1;
play scale1 (ens organ instru2) p2_2;
play scale1 (ens organ instru2) p2_3;
play scale1 (ens organ instru2) p2_4;
play scale1 (ens organ instru2) p2_5;
play scale1 instru1 p3_1;
play scale1 instru1 p3_2;
play scale1 instru1 p3_3;
play scale1 organ p4_1;
play scale1 organ p4_2;
play scale1 (organ 80) p4_3; 
play scale1 (chord (organ outro_organ) (instru1 outro_piano));
end;
