// Class handling conversion of youtube url to an mp3 file
class YouTubeConverter {


    constructor(formId) {
        this.form = document.getElementById(formId);
        this.form.addEventListener('submit', this.handleSubmit.bind(this));
    }

    // Event handler for submit button of the form
    handleSubmit(event) {
        event.preventDefault();
        const url = this.form.querySelector('#url').value;

        this.convertToMp3(url)
            .then(blob => this.downloadAudio(blob))
            .catch(error => this.handleError(error));
    }

    // Sends a post request to the api endpoint to convert the inputted url to an mp3 file
    convertToMp3(url) {
        return fetch('/api/convert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'url=' + encodeURIComponent(url),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error during conversion. Please try again later.');
            }
            return response.blob();
        });
    }

    // Downloads response blob to an mp3 file
    downloadAudio(blob) {
        const audioUrl = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = audioUrl;
        a.download = 'song.mp3';
        document.body.appendChild(a);
        a.click();
        a.remove();
    }


    // Throws an alert in case the convertToMp3 method returns an error
    handleError(error) {
        alert(error.message);
    }
}

// Usage
const converter = new YouTubeConverter('convertForm');
