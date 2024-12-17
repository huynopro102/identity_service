const song = document.querySelectorAll('.song')
const playlist = document.querySelectorAll('.playlist')
const btnsreach = document.querySelector('.btnsearch')
const cdcontrol = document.querySelector('.cd-thumb img')
const namesong = document.querySelector('header h2')
const audiomain = document.querySelector('#progress')
const inputmain = document.querySelector('.progress')
const btnplaypause = document.querySelector('.btn-toggle-play')
const iconplay = document.querySelector('.play')
const iconpause = document.querySelector('.pause')
const btnnext = document.querySelector('.btn-next')
const btnprev = document.querySelector('.btn-prev')
const containertag = document.querySelector('.container')
const btnprepeat = document.querySelector('.btn-repeat')
const cd_thumb = document.querySelector('.cd-thumb')
const currentTime = document.querySelector('.currentTime')
const durationTime = document.querySelector('.durationTime')
const btnaddsong = document.querySelector('.addsong')
const model = document.querySelector('.model')
const contentModel = document.querySelector('.contentModel')
const mypicture = document.querySelector('#mypicture')
const songincontentmodel = document.querySelector('.songinmodel .thumb')
const imginlabel = document.querySelector('.taglabel img')
const input1 = document.querySelector('.input1')
const input2 = document.querySelector('.input2')
const inputsubmit = document.querySelector('.inputsubmit')
const fileaudiomodel = document.querySelector('#fileaudiomodel')
const audiomodel = document.querySelector('#fileaudiomodel')
const audiomodelshow = document.querySelector('.audiomodelshow')
const playmodel = document.querySelector('.playmodel')
const pausemodel = document.querySelector('.pausemodel')
const inputmodelshow = document.querySelector('.inputmodelshow')
const firsttime = document.querySelector('.firsttime')
const endtime = document.querySelector('.endtime')
const iconrandom = document.querySelector('.btn-random')
const volumemain = document.querySelector('.volumemain')




const app = {
    currenIndex: 0,
    isPlaying: false,
    isReapeat: false,
    isModel: false,
    isRandom: false,
    playedsong: [],
    songs: [
        {
            name: '24h',
            singer: 'lyly',
            path: './music/24H _ OFFICIAL MUSIC VIDEO _  LYLY ft MAGAZINE.mp3',
            image: './images/24h.jpg'
        },
        {
            name: 'hymn for the weeked',
            singer: 'the British rock band Coldplay',
            path: './music/Coldplay -  Hymn For The Weekend (Live at The BRIT Awards 2016).mp3',
            image: './images/hymn_for_the_weeked.jpg'
        },
        {
            name: 'anh Đếch cần gì nhiều ngoài em',
            singer: 'đen vâu',
            path: './music/Đen - Anh Đếch Cần Gì Nhiều Ngoài Em ft. Vũ., Thành Đồng (M-V).mp3',
            image: './images/anh_dat_can_gi_nhieu_ngoai_em.jpg'
        },
        {
            name: 'diễn viên tồi',
            singer: 'đen vâu',
            path: './music/Đen - Diễn viên tồi ft. Thành Bùi, Cadillac (M-V).mp3',
            image: './images/dien_vien_toi.jpg'
        },
        {
            name: 'trốn tìm',
            singer: 'đen vâu',
            path: './music/Đen - Trốn Tìm ft. MTV band (M-V).mp3',
            image: './images/tron_tim.jpg'
        },
        {
            name: 'phố cũ còn anh',
            singer: 'Quinn ft Chilly',
            path: './music/Phố Cũ Còn Anh (Freak D Lofi Ver.) - Quinn ft Chilly.mp3',
            image: './images/pho_cu_con_anh.jpg'
        },
        {
            name: '2 triệu năm',
            singer: 'đen vâu',
            path: './music/y2meta.com - Đen - hai triệu năm ft. Biên (m_v) (320 kbps).mp3',
            image: './images/2_trieu_nam.jpg'
        },
        {
            name: 'yêu là thu thứ',
            singer: 'Only C',
            path: './music/Yêu Là _Tha Thu_ _ Only C _ Em Chưa 18 OST _ Official Music Video.mp3',
            image: './images/yeu_la_tha_thu.jpeg'
        },


    ],

    render: function () {
        let html = this.songs.map((value, index) => {

            return `
            
            <div class="song ${this.currenIndex === index ? 'active' : ''}  " >
            <div class="thumb">
            </div>
            <div class="body">
            <h3 class="title">${value.name}</h3>
            <p class="author">${value.singer}</p>
            </div>
            <div class="option">
            <i class="fas fa-ellipsis-h"></i>
            </div>
            </div>
            
            `
        })
        document.querySelector('.playlist').innerHTML = html.join('')
    },


    renderImage: function () {
        this.songs.map(function (value, index) {
            document.querySelectorAll('.thumb')[index].style.backgroundImage = `url('${value.image}')`
        })
    },

    searchbox: function () {
        const songrender = document.querySelectorAll('.song')
        const thumbrender = document.querySelectorAll('.title')
        btnsreach.oninput = function () {

            const newsearch = btnsreach.value.trim().toLowerCase()
            console.log(newsearch)
            thumbrender.forEach(function (value, index) {

                if (thumbrender[index].innerHTML.toLowerCase().includes(newsearch)) {
                    songrender[index].classList.remove('hide')
                } else {
                    songrender[index].classList.add('hide')
                }


            })
        }
    },

    loadsong: function () {

        cdcontrol.src = this.songs[this.currenIndex].image
        namesong.innerHTML = this.songs[this.currenIndex].name
        audiomain.src = this.songs[this.currenIndex].path
        containertag.style.backgroundImage = `url('${this.songs[this.currenIndex].image}')`

    },
    handleTime: function (x) {
        const minute = Math.floor(x / 60)
        const second = Math.ceil(x - (minute * 60))
        const total = `${minute}:${second < 10 ? `0` + second : second}`
        return total
    },

    handleEvents: function () {

        const _this = this


        // when oninput volume main
        volumemain.addEventListener('input',function(e){
            console.log(volumemain.value)
            const valueinputmain = volumemain.value
            audiomain.volume = valueinputmain
        })


        // when click into model

        model.addEventListener('click',(e)=>{
            if(e.target === e.currentTarget)   model.classList.toggle('hide')
        })




        // cd_thumb rotate follow the music
        const cd_thumbAnimte = cd_thumb.animate([
            { transform: 'rotate(360deg)' }
        ], {
            duration: 4500,
            iterations: Infinity,
        })
        cd_thumbAnimte.pause()


        // song progress
        audiomain.ontimeupdate = function () {
            const newcurrent = Math.floor(audiomain.currentTime / audiomain.duration * 100)
            inputmain.value = newcurrent

        }
        // when seeking audio
        inputmain.oninput = function (e) {

            const newseek = audiomain.duration / 100 * inputmain.value

            audiomain.currentTime = newseek


        }

        // handle time
        setInterval(function () {

            currentTime.innerHTML = _this.handleTime(audiomain.duration)
        }, 1000)

        setInterval(function () {

            durationTime.innerHTML = _this.handleTime(audiomain.currentTime)
        }, 1000)



        //  when clicking icon play  button
        btnplaypause.onclick = function () {
            if (!_this.isPlaying) {

                audiomain.play()

            } else {

                audiomain.pause()
            }
        }

        // when random song 



        // when audio onplay
        audiomain.onplay = function () {
            _this.isPlaying = true
            iconpause.classList.remove('hide')
            iconplay.classList.add('hide')
            audiomain.play()
            cd_thumbAnimte.play()

        }



        // when audio onpause
        audiomain.onpause = function () {
            _this.isPlaying = false
            iconplay.classList.remove('hide')
            iconpause.classList.add('hide')
            audiomain.pause()
            cd_thumbAnimte.pause()

        }


        // when clicking icon btnnext and add class active
        btnnext.onclick = function () {

            if (_this.currenIndex >= _this.songs.length - 1) {
                _this.currenIndex = 0
                _this.loadsong()
                _this.render()
                _this.renderImage()
                _this.addsong()
            } else {
                _this.currenIndex++
                _this.loadsong()
                const editsongactive = document.querySelectorAll('.song')
                _this.render()
                _this.renderImage()
                _this.addsong()


            }
            audiomain.play()
            _this.searchbox()

        }


        // when click icon btnprev and add class active 
        btnprev.onclick = function () {

            if (_this.currenIndex == 0) {
                _this.currenIndex = _this.songs.length - 1
                _this.loadsong()
                _this.render()
                _this.renderImage()

            } else {
                _this.currenIndex--
                _this.loadsong()
                const editsongactive = document.querySelectorAll('.song')
                _this.render()
                _this.renderImage()



            }
            audiomain.play()
            _this.searchbox()

        }


        // when ended 
        audiomain.onended = function () {
            if (_this.isReapeat) {
                audiomain.play()
            } else {
                if (_this.isRandom) {
                    _this.playrandomsong()
                    console.log(_this.playedsong)
                    audiomain.play()
                    _this.render()
                    _this.renderImage()
                } else {
                    btnnext.click()
                }
            }

        }


        // when click icon repeat 
        btnprepeat.onclick = function () {
            if (!_this.isReapeat) {
                btnprepeat.classList.add('iconactive')
                _this.isReapeat = true
                audiomain.play()
            } else {
                btnprepeat.classList.remove('iconactive')
                _this.isReapeat = false
            }

        }


        // when click icon random
        iconrandom.onclick = function () {
            if (_this.isRandom) {
                iconrandom.classList.remove('iconactive')
                _this.isRandom = false
            } else {
                iconrandom.classList.add('iconactive')
                _this.isRandom = true
            }
        }


    },


    playrandomsong: function () { 
        this.playedsong.push(this.currenIndex)
        do {
            var newCurrenIndex = Math.floor(Math.random() * this.songs.length)
            console.log(this.playedsong.length)
            if(this.playedsong.length === this.songs.length){
                break;
            } 
            //[1,2,3,4,5,6,7,]
        } while (this.playedsong.includes(newCurrenIndex))
   
        if (this.playedsong.length === this.songs.length) {
            this.playedsong = []
            newCurrenIndex = 0
            this.currenIndex = newCurrenIndex
            this.loadsong()
        
        } else{
            this.currenIndex = newCurrenIndex
            this.loadsong()
        }
    },

    addsong: function () {

        const isplaying = false
        const _this = this
        // open model
        btnaddsong.onclick = function () {
            model.classList.toggle('hide')
        }

        // add song
        mypicture.onchange = function () {

            // add image into thumb song
            const arrayfiles = mypicture.files
            const myURL = URL.createObjectURL(arrayfiles[0])
            songincontentmodel.style.backgroundImage = `url('${myURL}')`
            imginlabel.src = myURL

            // 

        }

        fileaudiomodel.onchange = function () {

            const arrayaudio = fileaudiomodel.files
            const myURLaudio = URL.createObjectURL(arrayaudio[0])
            audiomodelshow.src = myURLaudio

            audiomodelshow.play()
            playmodel.classList.add('hide')
            pausemodel.classList.remove('hide')
        }

        // when onclick into play and pause iconmodelshow
        playmodel.onclick = function () {
            audiomodelshow.play()
            playmodel.classList.add('hide')
            pausemodel.classList.remove('hide')

        }

        pausemodel.onclick = function () {
            audiomodelshow.pause()
            playmodel.classList.remove('hide')
            pausemodel.classList.add('hide')
        }

        // load audio into input model , i used to ontimeupdate
        audiomodelshow.ontimeupdate = function () {
            const newcurrentTime = audiomodelshow.currentTime / audiomodelshow.duration * 100
            inputmodelshow.value = newcurrentTime
        }

        // time frist , time end
        setInterval(function () {
            firsttime.innerHTML = _this.handleTime(audiomodelshow.currentTime)
        }, 1000)
        setInterval(function () {
            endtime.innerHTML = _this.handleTime(audiomodelshow.duration)
        }, 1000)


        // seek input model show

        // close and open model
        inputsubmit.onclick = () => {
            if (input1.value === '' && input2.value === "" || input1.value === '' || input2.value === '' || endtime.innerHTML === 'NaN:NaN') {

                alert('nhập tên bài hát và tác giả bài hát , thêm audio vô ')
            } else {
                model.classList.toggle('hide')

                const arrayfiles = mypicture.files
                const myURL = URL.createObjectURL(arrayfiles[0])

                const arrayaudio = fileaudiomodel.files
                const myURLaudio = URL.createObjectURL(arrayaudio[0])
                console.log(myURLaudio)

                const newarray = {
                    name: `${input1.value}`,
                    singer: `${input2.value}`,
                    path: `${myURLaudio}`,
                    image: `${myURL}`
                }
                _this.songs.push(newarray)
                _this.render()
                _this.renderImage()
                _this.searchbox()
                console.log(_this.songs)
                audiomodelshow.pause()

            }

        }


    },

    start: function () {

        this.render()

        this.renderImage()

        this.addsong()

        this.searchbox()

        this.loadsong()

        this.handleEvents()


        console.log(this.songs)
        console.log(this.playedsong.length,this.songs.length)
      
        
    },


}

app.start()




