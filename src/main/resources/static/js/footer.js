const $footer = document.querySelector('#footer')
footer()
function footer() {
  let html = `
    <footer class="page-footer bg-light pt-4">
      <div class="container-fluid text-center text-md-left">
        <div class="row">
          <div class="offset-2 col-md-4 mt-md-0 mt-3">
            <h5 class="text-uppercase">Footer Content</h5>
            <p>Here you can use rows and columns to organize your footer content.</p>
          </div>
          <hr class="clearfix w-100 d-md-none pb-3">
          <div class="col-md-3 mb-md-0 mb-3">
            <h5 class="text-uppercase">Links</h5>
            <ul class="list-unstyled">
              <li>
                <a href="#"> Link 1</a>
              </li>
              <li>
                <a href="#"> Link 2</a>
              </li>
              <li>
                <a href="#"> Link 3</a>
              </li>
              <li>
                <a href="#"> Link 4</a>
              </li>
            </ul>
          </div>
          <div class="col-md-3 mb-md-0 mb-3">
            <h5 class="text-uppercase">Links</h5>
            <ul class="list-unstyled">
              <li>
                <a href="#"> Link 1</a>
              </li>
              <li>
                <a href="#"> Link 2</a>
              </li>
              <li>
                <a href="#"> Link 3</a>
              </li>
              <li>
                <a href="#"> Link 4</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="footer-copyright text-center py-3">
        © 2021 KATA Programming Academy
      </div>
    </footer>
  `
  $footer.insertAdjacentHTML('beforeend', html)
}