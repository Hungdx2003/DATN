/**
* Template Name: NiceAdmin
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Updated: Apr 20 2024 with Bootstrap v5.3.3
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/

(function() {
  "use strict";

  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener 
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
      select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Search bar toggle
   */
  if (select('.search-bar-toggle')) {
    on('click', '.search-bar-toggle', function(e) {
      select('.search-bar').classList.toggle('search-bar-show')
    })
  }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
  let selectHeader = select('#header')
  if (selectHeader) {
    const headerScrolled = () => {
      if (window.scrollY > 100) {
        selectHeader.classList.add('header-scrolled')
      } else {
        selectHeader.classList.remove('header-scrolled')
      }
    }
    window.addEventListener('load', headerScrolled)
    onscroll(document, headerScrolled)
  }

  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  /**
   * Initiate tooltips
   */
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  })

  /**
   * Initiate quill editors
   */
 class ImageResize {
  constructor(quill, options = {}) {
    this.quill = quill;
    this.options = options;
    this.boxes = [];
    this.img = null;
    this.dragBox = null;
    this.dragStartX = 0;
    this.preDragWidth = 0;

    this.container = options.container || this.quill.root.parentNode || document.body;

    this.quill.root.addEventListener('click', (evt) => {
      if (evt.target && evt.target.tagName.toUpperCase() === 'IMG') {
        if (this.img === evt.target) return;
        if (this.img) this.hide();
        this.show(evt.target);
      } else if (this.img) {
        this.hide();
      }
    });
  }

  show(img) {
    this.img = img;
    this.showResizers();
  }

  hide() {
    this.img = null;
    this.hideResizers();
  }

  showResizers() {
    this.boxes = [];
    const rect = this.img.getBoundingClientRect();
    ['nw', 'ne', 'sw', 'se'].forEach(corner => {
      const box = document.createElement('div');
      box.classList.add('resize-handle');
      box.dataset.corner = corner;
      this.container.appendChild(box); // Sửa tại đây
      this.positionBox(box, rect, corner);
      box.addEventListener('mousedown', this.handleMousedown.bind(this));
      this.boxes.push(box);
    });
    document.addEventListener('mousemove', this.handleDrag);
    document.addEventListener('mouseup', this.handleMouseup);
  }

  hideResizers() {
    this.boxes.forEach(box => box.remove());
    this.boxes = [];
    document.removeEventListener('mousemove', this.handleDrag);
    document.removeEventListener('mouseup', this.handleMouseup);
  }

  positionBox(box, rect, corner) {
  // Vị trí ảnh so với container
  const containerRect = this.container.getBoundingClientRect();
  const positions = {
    nw: [rect.left - containerRect.left - 5, rect.top - containerRect.top - 5],
    ne: [rect.right - containerRect.left - 5, rect.top - containerRect.top - 5],
    sw: [rect.left - containerRect.left - 5, rect.bottom - containerRect.top - 5],
    se: [rect.right - containerRect.left - 5, rect.bottom - containerRect.top - 5],
  };
  const [left, top] = positions[corner];
  box.style.position = 'absolute';
  box.style.left = `${left}px`;
  box.style.top = `${top}px`;
}


  handleMousedown = (evt) => {
    this.dragBox = evt.target;
    this.dragStartX = evt.clientX;
    this.preDragWidth = this.img.width || this.img.offsetWidth;
    evt.preventDefault();
  };

  handleDrag = (evt) => {
    if (!this.dragBox) return;
    const deltaX = evt.clientX - this.dragStartX;
    this.img.width = this.preDragWidth + deltaX;
    const rect = this.img.getBoundingClientRect();
    this.boxes.forEach(box => {
      this.positionBox(box, rect, box.dataset.corner);
    });
  };

  handleMouseup = () => {
    this.dragBox = null;
  };
}

Quill.register('modules/imageResize', ImageResize);
// Khởi tạo các editor Quill với chức năng chỉnh kích thước ảnh
const quillOptions = {
  modules: {
    toolbar: [
      [{ font: [] }, { size: [] }],
      ["bold", "italic", "underline", "strike"],
      [{ color: [] }, { background: [] }],
      [{ 'header': '1' }, { 'header': '2' }, { 'header': '3' }, { 'header': '4' }, { 'header': '5' }, { 'header': '6' }],
      [{ script: "super" }, { script: "sub" }],
      [{ list: "ordered" }, { list: "bullet" }, { indent: "-1" }, { indent: "+1" }],
      ["direction", { align: [] }],
      ["link", "image", "video"],
      ["clean"]
    ],
    imageResize: {}
  },
  theme: "snow"
};
const contextPath = '/ToyStore_Admin';
function handleImageUpload(quill) {
  const toolbar = quill.getModule('toolbar');
  toolbar.addHandler('image', () => {
    const input = document.createElement('input');
    input.setAttribute('type', 'file');
    input.setAttribute('accept', 'image/*');
    input.click();

    input.onchange = () => {
      const file = input.files[0];
      if (file) {
        // Upload ảnh lên server
        const formData = new FormData();
        formData.append('post_image', file);

        fetch(contextPath+'/upload', {
          method: 'POST',
          body: formData,
        })
          .then((response) => response.json())
          .then((result) => {
            // Sau khi upload thành công, chèn ảnh vào Quill
            const range = quill.getSelection();
            quill.insertEmbed(range.index, 'image', result.url); // Chèn ảnh vào editor

            // Cập nhật URL ảnh vào input ẩn trong form
            document.getElementById('image-url').value = result.url;
          })
          .catch((error) => {
            console.error('Upload failed:', error);
          });
      }
    };
  });
}


// Khởi tạo Quill với các selector khác nhau và xử lý upload ảnh
function initQuillEditor(selector) {
  const quill = new Quill(selector, quillOptions);
  handleImageUpload(quill);
}

// Khởi tạo các Quill editor
if (select('.quill-editor-default')) {
  initQuillEditor('.quill-editor-default');
}

if (select('.quill-editor-bubble')) {
  initQuillEditor('.quill-editor-bubble');
}

if (select('.quill-editor-full')) {
  initQuillEditor('.quill-editor-full');
}

  /**
   * Initiate TinyMCE Editor
   */

  const useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
  const isSmallScreen = window.matchMedia('(max-width: 1023.5px)').matches;

  tinymce.init({
    selector: 'textarea.tinymce-editor',
    plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons accordion',
    editimage_cors_hosts: ['picsum.photos'],
    menubar: 'file edit view insert format tools table help',
    toolbar: "undo redo | accordion accordionremove | blocks fontfamily fontsize | bold italic underline strikethrough | align numlist bullist | link image | table media | lineheight outdent indent| forecolor backcolor removeformat | charmap emoticons | code fullscreen preview | save print | pagebreak anchor codesample | ltr rtl",
    autosave_ask_before_unload: true,
    autosave_interval: '30s',
    autosave_prefix: '{path}{query}-{id}-',
    autosave_restore_when_empty: false,
    autosave_retention: '2m',
    image_advtab: true,
    link_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_class_list: [{
        title: 'None',
        value: ''
      },
      {
        title: 'Some class',
        value: 'class-name'
      }
    ],
    importcss_append: true,
    file_picker_callback: (callback, value, meta) => {
      /* Provide file and text for the link dialog */
      if (meta.filetype === 'file') {
        callback('https://www.google.com/logos/google.jpg', {
          text: 'My text'
        });
      }

      /* Provide image and alt text for the image dialog */
      if (meta.filetype === 'image') {
        callback('https://www.google.com/logos/google.jpg', {
          alt: 'My alt text'
        });
      }

      /* Provide alternative source and posted for the media dialog */
      if (meta.filetype === 'media') {
        callback('movie.mp4', {
          source2: 'alt.ogg',
          poster: 'https://www.google.com/logos/google.jpg'
        });
      }
    },
    height: 600,
    image_caption: true,
    quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    noneditable_class: 'mceNonEditable',
    toolbar_mode: 'sliding',
    contextmenu: 'link image table',
    skin: useDarkMode ? 'oxide-dark' : 'oxide',
    content_css: useDarkMode ? 'dark' : 'default',
    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
  });

  /**
   * Initiate Bootstrap validation check
   */
  var needsValidation = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(needsValidation)
    .forEach(function(form) {
      form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

  /**
   * Initiate Datatables
   */
  const datatables = select('.datatable', true)
  datatables.forEach(datatable => {
    new simpleDatatables.DataTable(datatable, {
      labels: {
        placeholder: "Tìm kiếm ...",
        noRows: "Không có dữ liệu để hiển thị",
        info: "Hiển thị {start} đến {end} của {rows} mục",
    },
      perPageSelect: false,
      perPage: 5 ,
      columns: [{
          select: 2,
          sortSequence: ["desc", "asc"]
        },
        {
          select: 3,
          sortSequence: ["desc"]
        },
        {
          select: 4,
          cellClass: "green",
          headerClass: "red"
        }
      ]
    });
  })

  /**
   * Autoresize echart charts
   */
  const mainContainer = select('#main');
  if (mainContainer) {
    setTimeout(() => {
      new ResizeObserver(function() {
        select('.echart', true).forEach(getEchart => {
          echarts.getInstanceByDom(getEchart).resize();
        })
      }).observe(mainContainer);
    }, 200);
  }

})();