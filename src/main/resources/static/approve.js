function approveUser(userId) {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        alert('User approved successfully!');
        // Refresh the current page
        location.reload();
      } else {
        alert('There was an error while approving the user.');
      }
    }
  };
  xhr.open('POST', '/approveUser');
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send('id=' + userId);
}

function unapproveUser(userId) {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        alert('User unapproved successfully!');
        // Refresh the current page
        location.reload();
      } else {
        alert('There was an error while unapproving the user.');
      }
    }
  };
  xhr.open('POST', '/unapproveUser');
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send('id=' + userId);
}
